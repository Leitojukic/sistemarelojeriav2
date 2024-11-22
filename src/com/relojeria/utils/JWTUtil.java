package com.relojeria.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.util.Date;

public class JWTUtil {
    private static final String SECRET_KEY = "supersecretkey";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);
    private static final long EXPIRATION_TIME = 86400000; // 1 día en milisegundos

    public static String generarToken(String usuario, String rol) {
        return JWT.create()
                .withSubject(usuario)
                .withClaim("rol", rol)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(ALGORITHM);
    }

    public static boolean verificarToken(String token) {
        try {
            JWT.require(ALGORITHM).build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            System.out.println("Token inválido: " + e.getMessage());
            return false;
        }
    }

    public static String obtenerRolDesdeToken(String token) {
        return JWT.decode(token).getClaim("rol").asString();
    }
}
