package com.example.aopdemo.configure;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GeneralConfig {

    @Bean
    @LoadBalanced
    public RestTemplate provideRestTemplate() {
        return new RestTemplate();
    }
}
