package com.doctoreappointmentProject.doctoreappointmentProject.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private  final  String secret="MySuperSecertKey3454";


    private Key getSigningKey() {

        return Keys.hmacShaKeyFor(secret.getBytes());

    }

    public String generateToken(String username, String role){

        return  Jwts.builder()
                .setSubject(username)
                .claim("role",role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public Claims parseToken(String token)
    {
               return  Jwts.parserBuilder()
                       .setSigningKey(getSigningKey())
                       .build()
                       .parseClaimsJws(token)
                       .getBody();
    }




}
