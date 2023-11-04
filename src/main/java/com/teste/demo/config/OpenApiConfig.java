package com.teste.demo.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.Links;

@Configuration
public class OpenApiConfig {


    @Bean
    public OpenAPI customOpenAPI(){
        //SpringDocUtils.getConfig().addResponseTypeToIgnore(Links.class);

        return new OpenAPI().info(
                new Info()
                .title("Spring Boot API")
                .version("v1")
                .description("Alguns Testes")
                .termsOfService("https://www.google.com.br")
                .license(new License().name("Apache 2.0").url(""))
        );
    }


}
