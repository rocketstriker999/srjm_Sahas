package com.hammerbyte.sahas.services;

import org.springframework.security.core.Authentication;


public interface ServiceJWT {

    public String createJWT(Authentication authentication);

    public Authentication validateJWT(String requestHeader);
} 