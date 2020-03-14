package com.jitexam.jitexam.controller;

import com.jitexam.jitexam.security.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LoginController {

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", "user")
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + 20 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, user.getPassword())
                .compact();

    }
}
