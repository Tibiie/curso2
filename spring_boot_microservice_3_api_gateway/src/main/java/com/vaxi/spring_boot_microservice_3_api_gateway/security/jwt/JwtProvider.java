package com.vaxi.spring_boot_microservice_3_api_gateway.security.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.vaxi.spring_boot_microservice_3_api_gateway.security.UserPrincipal;

import jakarta.servlet.http.HttpServletRequest;

public interface JwtProvider {

    String generateToken(UserPrincipal auth);

    public UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request);
}
