package com.example.aopdemo.services.impl;

import com.example.aopdemo.exceptions.NotAllowedException;
import com.example.aopdemo.models.Vehicle;
import com.example.aopdemo.repositories.VehicleRepository;
import com.example.aopdemo.services.VehicleService;
import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class VehicleServiceImpl implements VehicleService {


    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private Gson gson;

    @Autowired
    private VehicleRepository vehicleRepository;


    @Override
    public Map<String, Object> createStudent(Vehicle vehicle) {
        if (vehicle.getVehicleRnNumber() == null || vehicle.getChassisNo() == null) {
            throw new NotAllowedException("Mandatory fields should not be empty");
        }
        if (vehicle.getVehicleId() != null) {
            //TODO call device gateWays will implement
        }
//        vehicle.setVehicleId(UUID.randomUUID().toString());
//        vehicle.setTimeStamp(System.currentTimeMillis());
//        MongoCollection mongoCollection = mongoTemplate.getCollection("VehicleData");
//        String str="{/"name"/":"/aman/"}";

//        vehicle.setChassisNo("Nishant Kumar");
//        String json = gson.toJson(vehicle);
//        Document document = Document.parse(json);
//        document.put("id", UUID.randomUUID().toString());
//        mongoCollection.insertOne(document);
        vehicleRepository.createVehicle(vehicle);
        Map<String, Object> response = new HashMap<>();
        response.put("error", null);
//        response.put("data", createdVehicle);
        response.put("status", HttpStatus.OK);
        mongoTemplate.insert(vehicle);
        return response;
    }

    @Override
    public Map<String, Object> updateStudent(Vehicle vehicle) {

        Map<String, Object> response = new HashMap<>();

        return response;
    }

    @Override
    public Map<String, Object> getStudentByID(String vehicleId) {
//        System.out.println(mongoTemplate.findByVehicleId(vehicleId));
        return null;
    }

    @Override
    public Map<String, Object> deleteStudentById(String vehicleId) {
        return null;
    }
}
