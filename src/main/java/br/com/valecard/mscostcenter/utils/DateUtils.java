package br.com.valecard.mscostcenter.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateUtils {

    public static final int HOUR_INDEX = 0;
    public static final int MINUTE_INDEX = 1;
    public static final int SECOND_INDEX = 2;
    public static final int MINUTES_IN_HOURS = 60;
    public static final int SECONDS_IN_HOURS = 3600;

    private static DateUtils instance;

    public static DateUtils getInstance() {
        if ( instance == null ) {
            instance = new DateUtils();
        }
        return instance;
    }

    /**
     * Retorna a diferença em dias entre duas datas com valor decimal
     *
     * @param dataInicial
     * @param dataFinal
     * @return
     */
    public static double getDiffDays( Date dataInicial, Date dataFinal ) {
        double result = 0;
        long diferenca;
        if ( dataFinal.getTime() > dataInicial.getTime() ) {
            diferenca = dataFinal.getTime() - dataInicial.getTime();
        } else {
            diferenca = dataInicial.getTime() - dataFinal.getTime();
        }

        double diferencaEmDias = (diferenca / 1000) / 60 / 60 / 24; // resultado
        // é
        // diferença
        // entre as
        // datas em
        // dias
        long horasRestantes = (diferenca / 1000) / 60 / 60 % 24; // calcula as
        // horas
        // restantes
        result = diferencaEmDias + (horasRestantes / 24d); // transforma as
        // horas restantes em
        // fração de dias

        return result;
    }

    public Date truncarData( Date data ) {

        if ( data != null ) {
            Calendar cal = Calendar.getInstance();
            cal.setTime( data );
            cal.set( Calendar.HOUR_OF_DAY, 0 );
            cal.set( Calendar.MINUTE, 0 );
            cal.set( Calendar.SECOND, 0 );
            cal.set( Calendar.MILLISECOND, 0 );
            return cal.getTime();
        }
        System.err.println( "DateUtil:truncarData() - Data nula passada como parâmetro." );
        return null;
    }

    public static Date toDate( String data ) {
        if ( data == null || data.equals( "" ) )
            return null;

        Date date = null;

        try {
            DateFormat formatter = new SimpleDateFormat( "dd/MM/yyyy" );
            date = formatter.parse( data );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date addHoras( Date data, String hora ) {
        try {
            SimpleDateFormat df = new SimpleDateFormat( "dd-MM-yyyy HH:mm:ss" );
            Date dataHora = truncData( data );
            if ( hora != null && !hora.equals( "" ) )
                dataHora = df.parse( new SimpleDateFormat( "dd-MM-yyyy" ).format( dataHora ) + " " + hora );

            return dataHora;
        } catch (ParseException p) {
            return null;
        }
    }

    public static Date truncData( Date data ) {
        Calendar dataConsulta = new GregorianCalendar();
        dataConsulta.setTime( data );
        dataConsulta = new GregorianCalendar( dataConsulta.get( Calendar.YEAR ), dataConsulta.get( Calendar.MONTH ),
                dataConsulta.get( Calendar.DATE ) );
        return dataConsulta.getTime();
    }

    public static int getDiaSemana( Date data ) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime( data );
        return calendar.get( Calendar.DAY_OF_WEEK );
    }

    public static Date adicionarDias( Date data, int quantidadeDias ) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( data );
        calendar.add( Calendar.DAY_OF_MONTH, quantidadeDias );
        return calendar.getTime();
    }

    public String getDataFormatadaToStr( Date data ) {
        if ( data != null ) {
            Format formatter;
            formatter = new SimpleDateFormat( "dd/MM/yyyy" );
            return formatter.format( data );
        }
        return null;
    }

    public static String dateToString( Date date ) {
        if ( date == null ) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat( "dd/MM/yyyy" );
        return format.format( (date) );
    }

    public static String dateToStringWithHours( Date date ) {
        if ( date == null ) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );
        return format.format( (date) );
    }

    public static java.sql.Date convertDateToSqlDate( Date data ) {
        if ( data == null ) {
            return null;
        } else {
            return new java.sql.Date( data.getTime() );
        }
    }

    /**
     * Retonar a data no primeiro dia do mes em questão
     *
     * @param data
     * @return
     */
    public static Date getFirstDayOfMonth( Date data ) {

        if ( data != null ) {
            Calendar cal = Calendar.getInstance();
            cal.setTime( data );
            cal.set( Calendar.DAY_OF_MONTH, 1 );
            return truncData( cal.getTime() );
        }
        System.err.println( "DateUtil:truncarData() - Data nula passada como parâmetro." );
        return null;
    }

    /**
     * Retonar uma data somando a quatidade de meses passada por parametros
     *
     * @param data
     * @param qtd
     * @return
     */
    public static Date addMonths( Date data, int qtd ) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( data );
        calendar.add( Calendar.MONTH, qtd );
        return calendar.getTime();
    }

    public static Date formattDate( String date ) {
        if ( date != null ) {
            SimpleDateFormat sdfEntrada = new SimpleDateFormat( "dd/MM/yyyy" );
            try {
                Date data = sdfEntrada.parse( date );
                return data;
            } catch (ParseException e) {
                System.err.println( "Favor digitar a data no formato informado." );
            }
        }
        return null;
    }

    public static Date convertXMLGregorianCalendarToDate( XMLGregorianCalendar xmlDate ) {
        GregorianCalendar gc = xmlDate.toGregorianCalendar();
        return gc.getTime();
    }

    public static Integer getDiaAtual() {
        Calendar c = Calendar.getInstance();
        c.setTime( new Date() );
        return c.get( Calendar.DAY_OF_MONTH );
    }

    public static Date adicionarDiasUteis( Date data, Integer qtdeDiasAcrescentados ) {

        Calendar dataInicial = Calendar.getInstance();
        dataInicial.setTime( data );
        while ( qtdeDiasAcrescentados > 0 ) {
            dataInicial.add( Calendar.DAY_OF_MONTH, 1 );
            int diaDaSemana = dataInicial.get( Calendar.DAY_OF_WEEK );
            if ( diaDaSemana != Calendar.SATURDAY && diaDaSemana != Calendar.SUNDAY ) {
                --qtdeDiasAcrescentados;
            }
        }
        return dataInicial.getTime();
    }

    public static XMLGregorianCalendar dateToCalendar( Date date ) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime( date );
        XMLGregorianCalendar xmlCalendar = null;
        try {
            xmlCalendar = DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar( calendar );
        } catch (DatatypeConfigurationException e) {
            System.out.println( "DEU ERRRO" );
        }
        return xmlCalendar;
    }

    public static String getNameMonth( Date d ) {
        if ( d == null )
            return "";
        Locale local = new Locale( "pt", "BR" );
        DateFormat dateFormat = new SimpleDateFormat( "MMMM", local );
        return dateFormat.format( d );
    }

    public static long quantosMinutosSePassaram( Date data ) {
        LocalDateTime dataAlvo = Instant.ofEpochMilli( data.getTime() ).atZone(
                ZoneId.systemDefault() ).toLocalDateTime();
        return Duration.between( dataAlvo, LocalDateTime.now() ).toMinutes();
    }

    public static BigDecimal convertTimeToDecimal( String time ) {
        // Example of a valid time (hh:mm:ss):
        // 01:30:00

        String[] members = time.split( ":" );

        BigDecimal hours = new BigDecimal( members[HOUR_INDEX] );
        BigDecimal minutes = new BigDecimal( members[MINUTE_INDEX] );
        BigDecimal seconds = new BigDecimal( members[SECOND_INDEX] );

        BigDecimal minutesCalc = minutes.divide( BigDecimal.valueOf( MINUTES_IN_HOURS ), 12, RoundingMode.HALF_DOWN );
        BigDecimal secondsCalc = seconds.divide( BigDecimal.valueOf( SECONDS_IN_HOURS ), 12, RoundingMode.HALF_DOWN );

        return hours.add( minutesCalc ).add( secondsCalc );
    }

    /**
     * Solução para subistituir o new Date()
     *
     * @return Data Hora atual no formato Date baseada no LocalDateTime
     */
    public static Date dateNow() {
        return Date.from( LocalDateTime.now().atZone( ZoneId.systemDefault() ).toInstant() );
    }

}
