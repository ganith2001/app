package com.spring_boot.backend.restapi.utils;

import org.springframework.stereotype.Component;

import com.spring_boot.backend.restapi.entities.login;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
@Component
public class JwtUtils {

    private static String secret = "This_is_secret";
 //   private static long expiryDuration = 60 * 60;

    public String generateJwt(login user){

//        long milliTime = System.currentTimeMillis();
 //       long expiryTime = milliTime + expiryDuration * 1000;

       Date issuedAt = new Date();
 //       Date expiryAt = new Date(expiryTime);

        // claims
        Claims claims = Jwts.claims()
                .setIssuer("ganith@gmail.com")
                .setIssuedAt(issuedAt);
                
 
        // optional claims
       
  

        // generate jwt using claims
        return Jwts.builder()
                .setClaims(claims)
               .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

   
    
}
