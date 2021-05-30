package com.example.aopdemo.services;


import com.common.models.Vehicle;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;

import java.util.Map;

public interface VehicleService {

    Map<String, Object> createVehicle(String vehicle) throws JsonProcessingException;

    Map<String, Object> updateVehicle(Vehicle vehicle) throws Exception;

    Map<String, Object> getVehicleByID(String vehicleId);

    Map<String, Object> deleteVehicleById(String vehicleId) throws JsonProcessingException;

    Map<String, Object> getFields(String key, String value, String fields);

    Map<String, Object> getVehicle(JSONObject request) throws JsonProcessingException;

    Map<String, Object> assignVehicleOnDevice(String vehicleId, String vtsDeviceId, boolean flag) throws Exception;

}
