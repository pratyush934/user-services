package com.nagina_international.OMS_V1.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTService {

    private final String secretKey = "3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b";
    private final Long expirationTime = 3600000L;


    //extractUserName -> token
    //extractExpiration -> token
    //extractRole -> token
    //extractClaim -> token , claim
    //extractAllClaim -> token
    //signInKey
    //generateToken -> userDetails
    //generateToken -> Map<String, Object> claims ,userDetails
    //buildToken -> claims, userDetails, expiration
    //isTokenExpired -> token
    //isTokenValid -> token, userDetails

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpirationTime(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

//    public String extractRole(String token) {
//        return extractClaim(token, )
//    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJwt(token)
                .getBody();
    }

    public Key getSignInKey() {
        byte[] keys = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keys);
    }
}
