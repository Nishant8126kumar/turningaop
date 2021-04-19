package com.example.aopdemo.security;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserSecurity {
    public String generateToken() {
        Key key = new SecretKeySpec("ccccccevgncjdlkffjgtnhtukckldjvhghgfhfghfvkhvgclkccccccccevgncjicikdknrnthggdiilfidkvbriktifiuv".getBytes(),
                "SHA-256");
        Map<String, Object> claims = new HashMap<>();

//        OffsetDateTime offsetDateTime=OffsetDateTime.now();
        claims.put("userId", 12);
        claims.put("orgId", 15);
        claims.put("userName", "Nishant Kumar");
        claims.put("emailId", "nushant.kumar@turningcloud@12");
        claims.put("isIssueAt", System.currentTimeMillis());
        claims.put("isExpireTime", new Date(System.currentTimeMillis() + 1000));
        JwtBuilder jwtBuilder = Jwts.builder()
                .setIssuedAt(new Date())
                .setClaims(claims);
        return jwtBuilder.compact();
    }
}