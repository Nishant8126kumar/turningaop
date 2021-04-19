package com.example.aopdemo.controllers;

import com.example.aopdemo.models.Vehicle;
import com.example.aopdemo.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/vehicle")
public class VehicleController {

    @Autowired
    VehicleService studentService;


    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createStudent(@RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(studentService.createStudent(vehicle));
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStudent(@RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(studentService.updateStudent(vehicle));
    }

    @GetMapping(value = "/getbyid/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStudentByID(@PathVariable("vehicleId") String studentId) {
        return ResponseEntity.ok(studentService.getStudentByID(studentId));
    }

    @DeleteMapping(value = "/delete/{vehicleId}")
    public ResponseEntity<?> deleteStudentById(@PathVariable("studentId") String studentId) {
        return ResponseEntity.ok(studentService.deleteStudentById(studentId));
    }
}
