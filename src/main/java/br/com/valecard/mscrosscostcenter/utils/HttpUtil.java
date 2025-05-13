package br.com.valecard.mscrosscostcenter.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

import br.com.valecard.mscrosscostcenter.utils.exceptions.InvokeUrlHttpException;

import jakarta.servlet.http.HttpServletRequest;

public class HttpUtil {
    public static HttpServletRequest request;

    public static String sendRequest( String url, Map<String, String> parametros ) throws Exception {
        String finalUrl = createURL( url, parametros );

        SSLContext sslContext = SSLContext.getInstance( "TLS" );
        sslContext.init( null, new X509TrustManager[]{new HttpsTrustManager()}, new SecureRandom() );

        HttpClient client = HttpClient.newBuilder()
                .sslContext( sslContext )
                .followRedirects( HttpClient.Redirect.NEVER )
                .connectTimeout( Duration.ofSeconds( 10 ) )
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri( URI.create( finalUrl ) )
                .GET()
                .build();

        HttpResponse<Void> response = client.send( request, HttpResponse.BodyHandlers.discarding() );

        return response.headers().firstValue( "Location" ).orElse( null );
    }

    public static String createURL( String url, Map<String, String> parametros ) {
        StringBuilder sb = new StringBuilder( url ).append( "?" );

        Set<Entry<String, String>> set = parametros.entrySet();
        Iterator<Entry<String, String>> it = set.iterator();

        while ( it.hasNext() ) {
            Entry<String, String> entry = it.next();
            sb.append( entry.getKey() )
                    .append( "=" )
                    .append( entry.getValue() );
            if ( it.hasNext() ) {
                sb.append( "&" );
            }
        }

        return sb.toString();
    }

    public static void throwMessageException( HttpURLConnection httpConnection ) throws Exception {
        if ( httpConnection.getErrorStream() != null ) {
            try (Scanner s = new Scanner( httpConnection.getErrorStream() )) {
                s.useDelimiter( "\\Z" );
                String response = s.hasNext() ? s.next() : "";
                throw new InvokeUrlHttpException( response );
            }
        }
        throw new InvokeUrlHttpException( "Error while connection url http" );
    }

    public static HttpResponse<String> sendGet( String url, Map<String, String> parametros ) throws
            InvokeUrlHttpException {
        try {
            String finalUrl = createURL( url, parametros );

            SSLContext sslContext = SSLContext.getInstance( "TLS" );
            sslContext.init( null, new X509TrustManager[]{new HttpsTrustManager()}, new SecureRandom() );

            HttpClient client = HttpClient.newBuilder()
                    .sslContext( sslContext )
                    .followRedirects( HttpClient.Redirect.NEVER )
                    .connectTimeout( Duration.ofSeconds( 10 ) )
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri( URI.create( finalUrl ) )
                    .GET()
                    .build();

            return client.send( request, HttpResponse.BodyHandlers.ofString() );

        } catch (IOException | InterruptedException e) {
            throw new InvokeUrlHttpException( "Error while connecting to URL: " + e.getMessage() );
        } catch (Exception e) {
            throw new InvokeUrlHttpException( "Unexpected error: " + e.getMessage() );
        }
    }
}