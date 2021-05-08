package com.example.aopdemo.controllers;

import com.example.aopdemo.services.VehicleService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    public ResponseEntity<?> createStudent(@RequestBody String vehicle) throws JsonProcessingException {
        return ResponseEntity.ok(studentService.createStudent(vehicle));
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStudent(@RequestBody String vehicle) throws JsonProcessingException {
        return ResponseEntity.ok(studentService.updateStudent(vehicle));
    }

    @GetMapping(value = "/getbyid/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStudentByID(@PathVariable("vehicleId") String studentId) {
        return ResponseEntity.ok(studentService.getStudentByID(studentId));
    }

    @GetMapping(value = "/get/fields", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFields(@RequestParam("key") String key, @RequestParam("value") String value, @RequestParam("fields") String fields) {
        return ResponseEntity.ok(studentService.getFields(key, value, fields));
    }

    @DeleteMapping(value = "/delete/{vehicleId}")
    public ResponseEntity<?> deleteStudentById(@PathVariable("vehicleId") String vehicleId) throws JsonProcessingException {
        return ResponseEntity.ok(studentService.deleteStudentById(vehicleId));
    }
}
