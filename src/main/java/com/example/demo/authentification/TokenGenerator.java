package com.example.demo.authentification;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenGenerator {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {

        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        
        System.out.println("HHHHHHHHHHHHHHHAHHAHHAH");

        Date tokenCreationDate = new Date();
        Date tokenExpirationDate = new Date(tokenCreationDate.getTime() + jwtExpirationMs);

        String key = Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(tokenCreationDate)
                .setExpiration(tokenExpirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        
        System.out.println(key);
        
        return key;
    }
}