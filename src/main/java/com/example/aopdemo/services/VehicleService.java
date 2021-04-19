package com.example.aopdemo.services;

import com.example.aopdemo.models.Vehicle;

import java.util.Map;

public interface VehicleService {

    Map<String, Object> createStudent(Vehicle vehicle);

    Map<String, Object> updateStudent(Vehicle vehicle);

    Map<String, Object>     getStudentByID(String vehicleId);

    Map<String, Object> deleteStudentById(String vehicleId);
}
