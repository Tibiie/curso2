package com.vaxi.spring_boot_microservice_3_api_gateway.security.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vaxi.spring_boot_microservice_3_api_gateway.security.UserPrincipal;
import com.vaxi.spring_boot_microservice_3_api_gateway.utils.SecurityUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Arrays;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

public class JwtProviderImpl implements JwtProvider{

    @Value("${app.jwt.secret}")
    private String JWT_SECRET;

    @Value("${app.jwt.expiration-in}")
    private Long JWT_EXPIRATION_IN_MS;

    @Override
    public String generateToken(UserPrincipal auth){
        String authorities = auth.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));

        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
        .setSubject(auth.getUsername())
        .claim("roles", authorities)
        .claim("userId", auth.getId())
        .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS))
        .signWith(key, SignatureAlgorithm.HS512)
        .compact();
    }

    @Override
    public UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){
        Claims claims = extractClaims(request);

        if (claims == null) {
            return null;
        }

        String username = claims.getSubject();
        Long userId = claims.get("userId", Long.class);

        Set<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
        .map(SecurityUtils::convertToAuthority)
        .collect(Collectors.toSet());

        UserDetails userDetails = new  UserPrincipal.Builder()
        .username(username)
        .authorities(authorities)
        .id(userId)
        .build();


        if(username == null){
            return null;
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
    }

    public boolean isTokenValid(HttpServletRequest request){
        Claims claims = extractClaims(request);
        if(claims == null){
            return false;
        }
    }
    
    private Claims extractClaims(HttpServletRequest request){
        String token = SecurityUtils.extractAuthTokenFromRequest(request);

        if (token == null) {
            return null;
        } 

        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

        return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody();
    }
}
