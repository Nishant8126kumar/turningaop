package com.example.aopdemo.services.impl;

import com.common.models.Device;
import com.example.aopdemo.ApplicationConstant;
import com.example.aopdemo.exceptions.NotAllowedException;
import com.example.aopdemo.exceptions.ResourceNotFoundException;
import com.common.models.Vehicle;
import com.example.aopdemo.repositories.VehicleRepository;
import com.example.aopdemo.services.VehicleService;
import com.example.aopdemo.services.utils.BeanUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class VehicleServiceImpl implements VehicleService {


    @Autowired
    private Gson gson;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RestTemplate restTemplate;


    @Override
    public Map<String, Object> createVehicle(String vehicleObj) throws JsonProcessingException {
        Vehicle vehicle = objectMapper.readValue(vehicleObj, Vehicle.class);
        if (vehicle.getVehicleRnNumber() == null || vehicle.getChassisNo() == null) {
            throw new NotAllowedException("Mandatory fields should not be empty");
        }
        if (vehicleRepository.getVehicleByServKeyValue("_id", vehicle.getUuid()) != null) {
            throw new NotAllowedException("Already Created");
        }
        if (vehicle.getSource() == null) vehicle.setSource("TurningCloud");
        if (vehicle.getVtsDeviceId() != null) {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                headers.set("X-COM-PERSIST", "NO");
                headers.set("X-COM-LOCATION", "USA");
                HttpEntity<String> entity = new HttpEntity<>(headers);
                ResponseEntity<String> response = restTemplate.exchange("http://DEVICE-MANAGER/device/v1/assign/device?vehicleRnNo="+vehicle.getVehicleRnNumber()+"&vtsDeviceId=" + vehicle.getVtsDeviceId() + "", HttpMethod.PUT, entity, String.class);
                System.out.println(response);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new NotAllowedException("Exception Occurred While call Device gateway");
            }

        }
        vehicle.setStatus(ApplicationConstant.VEHICLE_CREATED);
        vehicle.setCreationTime(System.currentTimeMillis());
        vehicle.setUpdateTime(System.currentTimeMillis());
        vehicle.setUuid(UUID.randomUUID().toString());
        return vehicleRepository.createVehicle(vehicle);
    }

    @Override
    public Map<String, Object> updateVehicle(Vehicle forUpdate) throws Exception {
        if (forUpdate.getVehicleRnNumber() == null || forUpdate.getUuid() == null) {
            throw new NotAllowedException("mandatory field should not be empty");
        }
        Optional<Vehicle> optionalVehicle = Optional.ofNullable(vehicleRepository.getVehicleByServKeyValue("_id", forUpdate.getUuid()));
        if (optionalVehicle.isPresent()) {
            Vehicle existVehicle = optionalVehicle.get();
            BeanUtils.copy(existVehicle, forUpdate);
            existVehicle.setUpdateTime(System.currentTimeMillis());
            return vehicleRepository.updateVehicle(existVehicle);
        }
        throw new ResourceNotFoundException("Vehicle not found for update on this vehicleId=:" + forUpdate.getUuid());
    }

    @Override
    public Map<String, Object> getVehicleByID(String vehicleId) {
        Map<String, Object> response = new HashMap<>();
        Optional<Vehicle> optionalVehicle = Optional.ofNullable(vehicleRepository.vehicleByVehicleId(vehicleId));
        if (optionalVehicle.isPresent() && !optionalVehicle.get().getStatus().equalsIgnoreCase("DELETED")) {
            Vehicle vehicle = optionalVehicle.get();
            response.put("data", vehicle);
            response.put("massage", "Vehicle Found Successfully");
            response.put("status", HttpStatus.OK);
            return response;
        }
        throw new ResourceNotFoundException("Vehicle not found on this vehicle Id=:" + vehicleId);
    }

    @Override
    public Map<String, Object> deleteVehicleById(String vehicleId) throws JsonProcessingException {
        Map<String, Object> response = new HashMap<>();
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

    @Override
    public Map<String, Object> getFields(String key, String value, String fields) {
        Map<String, Object> response = new HashMap<>();
        List<String> projectionFields = new ArrayList();
        if (fields != null && !fields.isBlank()) {
            String[] includesFields = fields.split(",");
            projectionFields.addAll(Arrays.asList(includesFields));
        }
        JSONObject vehicleInfo = vehicleRepository.getVehicleByProjection(key, value, projectionFields);
        System.out.println(vehicleInfo);
        if (vehicleInfo != null) {
            response.put("data", vehicleInfo.toMap());
            response.put("status", 200);
            return response;
        }
        System.out.println(response);
        return null;
    }

    @Override
    public Map<String, Object> getVehicle(JSONObject request) throws JsonProcessingException {
        Map<String, Object> response = new HashMap();
        int offset = 0;
        int limit = 10;
        String sortBy = "_id";
        String sortIn = "ASC";
        List<String> includeField = new ArrayList<>();
        if (!request.isNull("offset")) {
            offset = request.getInt("offset");
        }
        if (!request.isNull("limit")) {
            limit = request.getInt("limit");
        }
        if (!request.isNull("sortBy")) {
            sortBy = request.getString("sortBy");
        }
        if (!request.isNull("sortIn")) {
            sortIn = request.getString("sortIn");
        }
        if (!request.isNull("includeFields")) {
            String[] fields = request.getString("includeFields").split(",");
            includeField.addAll(Arrays.asList(fields));
        }
        Optional<List<Vehicle>> optional = Optional.ofNullable(vehicleRepository.getVehicles(offset, limit, sortBy, sortIn, includeField));
        if (optional.isPresent()) {
            List<Vehicle> jsonA = optional.get();
            response.put("data", jsonA);
            response.put("error", null);
            response.put("massage", "vehicles found successfully");
            return response;
        }
        response.put("data", null);
        response.put("message", "Vehicle not found successfully");
        return response;
    }

    @Override
    public Map<String, Object> assignVehicleOnDevice(String vehicleRnNo, String vtsDeviceId, boolean flag) throws Exception {
        Optional<Vehicle> optionalVehicle = Optional.ofNullable(vehicleRepository.getVehicleByServKeyValue("vehicleRnNumber",vehicleRnNo));
        if (optionalVehicle.isPresent()) {
            System.out.println("here2");
            optionalVehicle.get();
            Vehicle vehicle = optionalVehicle.get();
            if (vehicle.getVtsDeviceId() != null) {
                if (flag) {
                    vehicle.setVtsDeviceId(vtsDeviceId);
                    vehicle.setTrackingEnabled(true);
                    System.out.println("Request For Update");
                    return updateVehicle(vehicle);
                } else
                    throw new NotAllowedException("This Device " + vtsDeviceId + " already associated with another vehicle");
            }
            vehicle.setVtsDeviceId(vtsDeviceId);
            return vehicleRepository.updateVehicle(vehicle);
        }
        throw new ResourceNotFoundException("Vehicle not found on this vehicleId=:" + vehicleRnNo + "");
    }

}

