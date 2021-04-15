package com.example.aopdemo.filter;

import com.example.aopdemo.exceptions.NotAllowedException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class ApplyFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        System.out.println("token=:" + token);
        if (token != null) {
            throw new NotAllowedException("User not Authenticate");
        }
        filterChain.doFilter(request, response);
    }

    public static void main(String[] args) {
        Key key = new SecretKeySpec(
                "ccccccevgncjdlkffjgtnhtukckldjvhghgfhfghfvkhvgclkccccccccevgncjicikdknrnthggdiilfidkvbriktifiuv".getBytes(),
                "SHA-256");
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", 12);
        claims.put("orgId", 15);
        claims.put("userName", "Nishant Kumar");
        claims.put("emailId", "nushant.kumar@turningcloud@12");
        claims.put("isIssueAt", System.currentTimeMillis());
        claims.put("isExpireTime", new Date(System.currentTimeMillis() + 1000));
        JwtBuilder jwtBuilder = Jwts.builder()
                .setIssuedAt(new Date())
                .setClaims(claims);
//                .signWith(SignatureAlgorithm.ES512, key);
        System.out.println(jwtBuilder.compact());
    }
}
