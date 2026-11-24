package com.Sameer.TheMessage.Service.Impl;

import com.Sameer.TheMessage.Entity.User;
import com.Sameer.TheMessage.Service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtServiceImplementation implements JwtService {
    private final SecretKey signingKey;
    private final long expirationMs;
    public JwtServiceImplementation(@Value("${jwt.secret}") String secretKey,
                                    @Value("${jwt.expiration}") long expirationMs){
        this.signingKey= Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        this.expirationMs=expirationMs;
    }



    //2)called on each subsequent request
    @Override
    public Long extractUserId(String token){
        Claims claim = extractAllClaims(token);
        return Long.parseLong(claim.getSubject());
    }
    @Override
    public String extractUserName(String token){
        Claims claim= extractAllClaims(token);
        return claim.get("username",String.class);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    //1)called by loginService when credential match to send the loginResponse
    @Override
    public String generateToken(User user) {
        Date now=new Date();
        Date expiry=new Date(now.getTime()+expirationMs);
        return Jwts.builder()
                .subject(String.valueOf(user.getId()))
                .claim("username",user.getUserName())
                .issuedAt(now)
                .expiration(expiry)
                .signWith(signingKey)
                .compact();
    }
}
