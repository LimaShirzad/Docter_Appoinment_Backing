package com.doctoreappointmentProject.doctoreappointmentProject.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private  static final Key key= Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private  static  final  long EXPIRATION_TIME=1000 *60 *60; //(1 hours)


    public static  String generateToken(String username,Long id,String role){

        return Jwts.builder()
                .setSubject(username)
                .claim("role",role)
                .claim("id",id)
                .setExpiration(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();

    }


    public  static String getUserNameFromToken(String token){



        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();  //  return username


    }

    public static String getRoleFromToken(String token) {
        return (String) Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .get("role");
    }




}
