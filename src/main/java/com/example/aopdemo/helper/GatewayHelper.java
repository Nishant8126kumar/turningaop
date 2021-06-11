package com.example.aopdemo.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class GatewayHelper {

    @Autowired
    RestTemplate restTemplate;
}
