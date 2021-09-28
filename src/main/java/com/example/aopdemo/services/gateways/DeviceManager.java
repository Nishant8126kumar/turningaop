package com.example.aopdemo.services.gateways;

import com.common.models.Vehicle;
import com.example.aopdemo.exceptions.NotAllowedException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
public class DeviceManager {

    @Autowired
    RestTemplate restTemplate;

    public boolean assignDeviceOnVehicle(Vehicle vehicle) {
        boolean result = false;
        String url = "http://DEVICE-MANAGER/device/v1/assign/device?vehicleRnNo=" + vehicle.getVehicleRegistrationNumber() + "&vtsDeviceId=" + vehicle.getVtsDeviceId();
        ResponseEntity<String> response = this.makePutRequestGetResponse(url, "");
        JSONObject jsonObject = new JSONObject(response.getBody());
        if (jsonObject.getInt("status") == 200) result = true;
        return result;
    }

    private ResponseEntity<String> makePutRequestGetResponse(String url, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("token", token);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
        System.out.println("Status Data" + responseEntity);
        return responseEntity;
    }
}


