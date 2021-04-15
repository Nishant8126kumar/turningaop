package com.example.aopdemo.swaggerimpl;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Component
public class Swagger {

    @Bean
    public Docket apiDoc() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.example.aopdemo")).build();
    }
}
// return new Docket(DocumentationType.SWAGGER_2).select()
//         .apis(RequestHandlerSelectors.basePackage("com.tutorialspoint.swaggerdemo")).build();