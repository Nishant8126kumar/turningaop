package com.example.aopdemo.services.impl;

import com.example.aopdemo.ApplicationConstant;
import com.example.aopdemo.exceptions.NotAllowedException;
import com.example.aopdemo.models.Vehicle;
import com.example.aopdemo.repositories.VehicleRepository;
import com.example.aopdemo.services.VehicleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class VehicleServiceImpl implements VehicleService {


    @Autowired
    private Gson gson;

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    ObjectMapper objectMapper;


    @Override
    public Map<String, Object> createStudent(String vehicleObj) throws JsonProcessingException {
        Vehicle vehicle = objectMapper.readValue(vehicleObj, Vehicle.class);
        if (vehicle.getVehicleRnNumber() == null || vehicle.getChassisNo() == null) {
            throw new NotAllowedException("Mandatory fields should not be empty");
        }
        if (vehicle.getVehicleId() != null) {
            //TODO call device gateWays will implement
        }
        vehicle.setStatus(ApplicationConstant.VEHICLE_CREATED);
        return vehicleRepository.createVehicle(vehicle);
    }

    @Override
    public Map<String, Object> updateStudent(String vehicleObj) throws JsonProcessingException {
        Vehicle vehicle = objectMapper.readValue(vehicleObj, Vehicle.class);
        if (vehicle.getVehicleId() == null || vehicle.getVehicleRnNumber() == null) {
            throw new NotAllowedException("mandatory field should not be empty");
        }
        return vehicleRepository.updateVehicle(vehicle);
    }

    @Override
    public Map<String, Object> getStudentByID(String vehicleId) {
        Map<String, Object> respone = new HashMap<>();
//        System.out.println(mongoTemplate.findByVehicleId(vehicleId));
//        respone.put("data",mongoTemplate.finb);
        return null;
    }

    @Override
    public Map<String, Object> deleteStudentById(String vehicleId) {
        return null;
    }
}
