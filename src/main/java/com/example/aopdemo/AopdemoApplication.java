package com.example.aopdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAspectJAutoProxy
@EnableSwagger2
@SpringBootApplication
public class AopdemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(AopdemoApplication.class, args);
    }

}
