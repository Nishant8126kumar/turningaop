package com.example.aopdemo.controllers;

import com.common.models.Vehicle;
import com.example.aopdemo.services.VehicleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;
    @Autowired
    ObjectMapper objectMapper;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createStudent(@RequestBody String vehicle) throws JsonProcessingException {
        return ResponseEntity.ok(vehicleService.createVehicle(vehicle));
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStudent(@RequestBody String request) throws Exception {
        Vehicle vehicle = objectMapper.readValue(request, Vehicle.class);
        return ResponseEntity.ok(vehicleService.updateVehicle(vehicle));
    }

    @GetMapping(value = "/getbyid/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStudentByID(@PathVariable("vehicleId") String vehicleId) {
        return ResponseEntity.ok(vehicleService.getVehicleByID(vehicleId));
    }

    @GetMapping(value = "/get/fields", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFields(@RequestParam("key") String key, @RequestParam("value") String value, @RequestParam("fields") String fields) {
        return ResponseEntity.ok(vehicleService.getFields(key, value, fields));
    }

    @DeleteMapping(value = "/delete/{vehicleId}")
    public ResponseEntity<?> deleteStudentById(@PathVariable("vehicleId") String vehicleId) throws JsonProcessingException {
        return ResponseEntity.ok(vehicleService.deleteVehicleById(vehicleId));
    }

    @GetMapping(value = "/get/vehicle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getVehicle(@RequestBody String request) throws JsonProcessingException {
        System.out.println(request);
        JSONObject jsonObject = new JSONObject(request);
        return ResponseEntity.ok(vehicleService.getVehicle(jsonObject));
    }

    @PutMapping(value = "/assign/device", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> assignVehicle(@RequestParam("vehicleId") String vehicleId, @RequestParam("vtsDeviceId") String vtsDeviceId, @RequestParam("flag") boolean flag) throws Exception {
        return ResponseEntity.ok(vehicleService.assignVehicleOnDevice(vehicleId, vtsDeviceId, flag));
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getVehicleData() throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.setCreationTime(System.currentTimeMillis());
        vehicle.setVehicleRnNumber("UP81BD8026");
        return ResponseEntity.ok(vehicle);
    }

}
