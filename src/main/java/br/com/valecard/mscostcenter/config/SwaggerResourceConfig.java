package br.com.valecard.mscostcenter.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "MS Cost Center",
                version = "1.0.0",
                description = "This Api should provide CRUD operations in order to serve MS Cost Center",
                contact = @Contact(name = "Valecard", url = "http://www.todo.com", email = "mail@mail.com"),
                termsOfService = "Terms of Service: TODO."
        ),
        servers = @Server(url = "/")
)
public class SwaggerResourceConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group( "mscostcenter" )
                .packagesToScan( "br.com.valecard.mscostcenter" )
                .pathsToMatch( "/**" )
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info( new io.swagger.v3.oas.models.info.Info()
                        .title( "MS Cost Center Api" )
                        .version( "1.0.0" )
                        .description( "This API provides CRUD operations for MS Cost Center API" )
                        .termsOfService( "Terms of Service: TODO." )
                        .license( new License()
                                .name( "License - Valecard" )
                                .url( "http://www.todo.com" ) )
                        .contact( new io.swagger.v3.oas.models.info.Contact()
                                .name( "Valecard" )
                                .email( "mail@mail.com" )
                                .url( "http://www.todo.com" ) )
                );
    }
}
