package com.hammerbyte.sahas.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hammerbyte.sahas.services.ServiceJWT;

import java.io.IOException;

@AllArgsConstructor
@Getter
@Setter
@Slf4j
public class JWTTokenValidatorFilter extends OncePerRequestFilter {

    private String jwtHeader;
    private ServiceJWT serviceJWT;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws IOException, ServletException {

        try {
            SecurityContextHolder.getContext().setAuthentication(serviceJWT.validateJWT(request.getHeader(jwtHeader)));
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            log.info("User " + authentication.getName() + " is successfully authenticated and "
                    + "has the getPrincipal " + authentication.getAuthorities().toString());

        } catch (Exception exception) {
            exception.printStackTrace();
           
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) throws ServletException {
        log.info("Checking For JWT Token....");
        String header = request.getHeader(jwtHeader);
        if (header == null || header.isEmpty()) {
            log.info("NO JWT Header Found....Direct Request came up");
            return true;
        } else {
            return false;
        }

    }

}
