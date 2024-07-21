package com.hammerbyte.sahas.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hammerbyte.sahas.services.ServiceJWT;

import java.io.IOException;

@AllArgsConstructor
@Getter
@Setter
public class JWTTokenValidatorFilter extends OncePerRequestFilter {

    
    private String jwtHeader;
    private ServiceJWT serviceJWT;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws IOException, ServletException {

        try {
            SecurityContextHolder.getContext().setAuthentication(serviceJWT.validateJWT(request.getHeader(jwtHeader)));
        } catch (Exception exception) {
            throw new BadCredentialsException("Invalid Token received!");
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) throws ServletException {
        String header = request.getHeader(jwtHeader);
        return header == null || header.isEmpty();
    }

}
