package com.example.aopdemo.controllers;

import com.example.aopdemo.security.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserLoginController {

    @Autowired
    UserSecurity userSecurity;



    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> userLogin() {
        Map<String, Object> response = new HashMap<>();
        response.put("token", userSecurity.generateToken());
        return ResponseEntity.ok(response);
    }

    public static void main(String[] args) {
        String str = "site_code,name1";
        String s = "DESC";
        String[] newString = str.split(",");
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < newString.length; i++) {
            System.out.println(newString[i]);
            stringBuffer.append(newString[i]).append(" ").append(s).append(",");
        }
        String a = stringBuffer.substring(0, stringBuffer.length() - 1);
        System.out.println(a);
    }
}

