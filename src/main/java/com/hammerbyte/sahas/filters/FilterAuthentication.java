package com.hammerbyte.sahas.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hammerbyte.sahas.services.ServiceJWT;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
@Component
public class FilterAuthentication extends OncePerRequestFilter {

    @Value("${app.jwt.header}")
    private String jwtHeader;

    @lombok.NonNull
    private ServiceJWT serviceJWT;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws IOException, ServletException {

        try {
            SecurityContextHolder.getContext().setAuthentication(serviceJWT.validateJWT(request.getHeader(jwtHeader)));
        } catch (Exception ex) {
            log.info("JWT Authentication Failed" );
        }
        finally{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            
            if(authentication!=null && authentication.isAuthenticated()){
                log.info(authentication.getName() + " - "+authentication.getAuthorities().toString()+" Validated" );
                filterChain.doFilter(request, response);
            }else{
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) throws ServletException {
        String header = request.getHeader(jwtHeader);
        return header == null || header.isEmpty();
    }

    

}
