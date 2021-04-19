package com.example.aopdemo.repositories;

import com.example.aopdemo.models.Vehicle;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private Gson gson;


    public void createVehicle(Vehicle vehicle) {
//        MongoCollection mongoCollection = mongoTemplate.getCollection("VehicleData");
//        vehicle.setChassisNo("aman sharma");
//        String jsonString = gson.toJson(vehicle);
//        Document document = Document.parse(jsonString);
//        document.put("_id", UUID.randomUUID().toString());
//        mongoCollection.insertOne(document);
    }
}
