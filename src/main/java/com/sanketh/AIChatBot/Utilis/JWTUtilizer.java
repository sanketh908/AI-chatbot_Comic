package com.sanketh.AIChatBot.Utilis;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtilizer {
    @Value("${jwt.secret.key}")
    private  String SECRET_KEY ;
    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<String, Object>();
       return createToken(claims,username);

    }
    public String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .header().empty().add("typ", "JWT").add("alg", "HS256")
                .and()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(getSecretKey())
                .compact();
    }
    public
}
