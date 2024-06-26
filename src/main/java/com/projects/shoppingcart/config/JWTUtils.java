package com.projects.shoppingcart.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projects.shoppingcart.dto.auth.TokenDto;
import com.projects.shoppingcart.error.BadRequestAlertException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTUtils {

    private static final String JWT_ISSUER = "shopping-cart";
    private static final String JWT_SECRET = "OJDW93R02C289M39920XM";
    private static final Long JWT_EXPIRATION = 86400000L;

    public static String generateJWTToken(TokenDto tokenDto) {
        try {
            Date expiration = new Date(System.currentTimeMillis() + JWT_EXPIRATION);
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);

            return JWT.create()
                    .withIssuer(JWT_ISSUER)
                    .withIssuedAt(new Date())
                    .withExpiresAt(expiration)
                    .withSubject(tokenDto.getUsername())
                    .withClaim("role", tokenDto.getRole())
                    .withClaim("email", tokenDto.getEmail())
                    .withClaim("firstName", tokenDto.getFirstName())
                    .withClaim("lastName", tokenDto.getLastName())
                    .withClaim("roleId", tokenDto.getRoleId())
                    .withClaim("username", tokenDto.getUsername())
                    .sign(algorithm);
        } catch (Exception e) {
            throw new BadRequestAlertException(e.getMessage(), "JWTUtils", "generateJWTToken");
        }
    }

    public static Boolean validateJWTToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);

            JWT.require(algorithm)
                    .withIssuer(JWT_ISSUER)
                    .build()
                    .verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getUsernameFromToken(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getSubject();
        } catch (Exception e) {
            throw new BadRequestAlertException("Invalided token", "JWTUtils", "generateJWTToken");
        }
    }

    public static Boolean isTokenExpired(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getExpiresAt().before(new Date());
        } catch (Exception e) {
            throw new BadRequestAlertException("Invalided token", "JWTUtils", "generateJWTToken");
        }
    }

    public static TokenDto getTokenDetails(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            String details = decodedJWT.getClaim("details").asString();

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(details, TokenDto.class);

        } catch (Exception e) {
            throw new BadRequestAlertException("Invalided token", "JWTUtils", "generateJWTToken");
        }
    }

    public static void TokenVerification(String auth) {
        try {
            String[] parts = auth.split(" ");
            String token;
            if (parts.length < 2) {
                token = parts[0];
            } else {
                token = parts[1];
            }
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(JWT_ISSUER).build();
            verifier.verify(token);
        } catch (Exception e) {
            if (e.getMessage() == null) {
                throw new BadRequestAlertException("Unauthorized", "JWTUtils", "generateJWTToken");
            } else {
                throw new BadRequestAlertException(e.getMessage(), "JWTUtils", "generateJWTToken");
            }
        }
    }
}