package br.com.valecard.mscrosscostcenter;

//import br.com.valecard.libs.config.security.SharedAutoSecurityConfiguration;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@EnableAutoConfiguration
//@ImportAutoConfiguration(value = {
//        SharedAutoSecurityConfiguration.class
//})
public class Main implements ApplicationRunner {

    @Value("${url.producer.kafka}")
    private String urlProducer;

    public static void main( String[] args ) {
        Locale.setDefault( new Locale( "pt", "BR" ) );
        SpringApplication.run( Main.class, args );
    }

    @Override
    public void run( ApplicationArguments args ) throws Exception {
        System.setProperty( "urlProducer", urlProducer );
    }
}
