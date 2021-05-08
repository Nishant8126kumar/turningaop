package com.example.aopdemo.services.impl;

import com.example.aopdemo.ApplicationConstant;
import com.example.aopdemo.exceptions.DBException;
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
import java.util.UUID;

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
        if (vehicleRepository.getVehicleByServKeyValue("_id", vehicle.getUuid()) != null) {
            throw new NotAllowedException("Already Created");
        }
        if (vehicle.getSource() == null) vehicle.setSource("TurningCloud");
        vehicle.setStatus(ApplicationConstant.VEHICLE_CREATED);
        vehicle.setCreationTime(System.currentTimeMillis());
        vehicle.setUpdateTime(System.currentTimeMillis());
        vehicle.setUuid(UUID.randomUUID().toString());
        return vehicleRepository.createVehicle(vehicle);
    }

    @Override
    public Map<String, Object> updateStudent(String vehicleObj) throws JsonProcessingException {
        Vehicle vehicle = objectMapper.readValue(vehicleObj, Vehicle.class);
        if (vehicle.getVehicleRnNumber() == null) {
            throw new NotAllowedException("mandatory field should not be empty");
        }
        Vehicle existVehicle = vehicleRepository.getVehicleByServKeyValue("_id", vehicle.getUuid());
        existVehicle.setUpdateTime(System.currentTimeMillis());
        return vehicleRepository.updateVehicle(vehicle);
    }

    @Override
    public Map<String, Object> getStudentByID(String vehicleId) {
        Map<String, Object> response = new HashMap<>();
        Vehicle vehicle = vehicleRepository.vehicleByVehicleId(vehicleId);
        if (vehicle != null && !vehicle.getStatus().equals("DELETED")) {
            response.put("data", vehicle);
            response.put("massage", "Vehicle Found Successfully");
            response.put("status", HttpStatus.OK);
            return response;
        }
        response.put("data", null);
        response.put("massage", "No Record found");
        response.put("status", HttpStatus.BAD_REQUEST);
        return response;
    }

    @Override
    public Map<String, Object> deleteStudentById(String vehicleId) throws JsonProcessingException {
        Map<String, Object> response = new HashMap();
        Vehicle vehicle = vehicleRepository.getVehicleByServKeyValue("_id", vehicleId);
        if (vehicle == null) {
            throw new NotAllowedException("no vehicle present on this id " + vehicleId + "");

        }
        vehicle.setStatus(ApplicationConstant.VEHICLE_DELETED);
        vehicle.setUpdateTime(System.currentTimeMillis());
        vehicleRepository.updateVehicle(vehicle);
        response.put("msg", "vehicle deleted successfully");
        return response;
    }
}

