package com.example.aopdemo.services;

import com.example.aopdemo.models.Vehicle;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public interface VehicleService {

    Map<String, Object> createStudent(String vehicle) throws JsonProcessingException;

    Map<String, Object> updateStudent(String vehicle) throws JsonProcessingException;

    Map<String, Object>     getStudentByID(String vehicleId);

    Map<String, Object> deleteStudentById(String vehicleId);
}
