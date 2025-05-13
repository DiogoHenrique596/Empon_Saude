package br.com.valecard.mscrosscostcenter.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"br.com.valecard.mscrosscostcenter.*"})
@EnableCaching
public class AppWebConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings( CorsRegistry registry ) {
        registry.addMapping( "/**" );
    }

    @Autowired
    private Environment env;

    /**
     * Resposible for allow the file uploading
     *
     * @return
     */
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    /**
     * Responsible for enable the Internationalization
     *
     * @return
     */
    @Override
    public void addInterceptors( InterceptorRegistry registry ) {
        registry.addInterceptor( new LocaleChangeInterceptor() );
    }

}