package com.Airbnb.service;


import com.Airbnb.entity.PropertyUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class JWTService {

    @Value("${jwt.algorithm.key}")
    private String algorithmkey;

    @Value("${jwt.issure}")
    private String issure;

    @Value("${jwt.expiry.duration}")
    private int expiryTime;


    private Algorithm algorithm;

    @PostConstruct
    public void postConstruct() {

      algorithm=  Algorithm.HMAC256(algorithmkey);

    }

    private  final static  String USER_NAME = "username";

    String generateToken(PropertyUser propertyUser)
    {
     return  JWT.create().
                withClaim(USER_NAME,propertyUser.getUserName())
                .withExpiresAt(new Date(System.currentTimeMillis()+expiryTime))
                .withIssuer(issure)
                .sign(algorithm);

    }

}
