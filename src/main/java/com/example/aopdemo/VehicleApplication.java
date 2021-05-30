package com.example.aopdemo;

import com.mongodb.client.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.core.MongoTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAspectJAutoProxy
@EnableSwagger2
@SpringBootApplication
public class VehicleApplication {
    @Autowired
    MongoTemplate mongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(VehicleApplication.class, args);
    }

    @Bean
    public MongoCollection provideMongoColletiion() {
        MongoCollection mongoCollection = mongoTemplate.getCollection("VehicleData");
        return mongoCollection;
    }
}
