package com.example.aopdemo.configure;

import com.example.aopdemo.repositories.VehicleRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfiguration {


    @Bean
    public com.mongodb.client.MongoClient provideMongoClient() {
        return MongoClients.create("mongodb+srv://Nishant:6TIgVOhb326FkIMO@cluster0.wjfgs.mongodb.net/test");
//        return new MongoClient("localhost", 27017);
    }

    @Bean
    public MongoDatabase proviveMongoDataBase(MongoClient mongoClient) {
        return mongoClient.getDatabase("Fretron");
    }

    @Bean
    public VehicleRepository provideMongoDataBase(MongoDatabase mongoDatabase) {
        return new VehicleRepository(mongoDatabase);
    }

//    @Bean
//    public MongoCollection mongoCollection() {
//        return new Mongo;
//    }

//    @Bean
//    public MongoCollection provideMongoColletiion() {
//        MongoCollection mongoCollection = mongoTemplate.getCollection("VehicleData");
//        return mongoCollection;
//    }
}
