package com.example.EcoBytes.security;


import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class JwtService {
    private static final String SECRET = "myverysecuresecretkeymyverysecuresecretkey123hashandjjfhdjh";
    private static final long EXPIRATION = 60000;

    // Generate token for user
    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();

    }

    // Extract username
    public String extractUsername(String token) throws ExpiredJwtException, JwtException{
        return extractAllClaims(token).getSubject();

    }

    // Validate token
    public boolean validateToken(String token, UserDetails userDetails) throws JwtException{
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername());
    }

    private Claims extractAllClaims(String token) throws JwtException{
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();

    }



}
