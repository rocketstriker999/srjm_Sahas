package com.hammerbyte.sahas.services.impl;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import com.hammerbyte.sahas.services.ServiceJWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class ImplServiceJWT implements ServiceJWT {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expire}")
    private Long jwtExpire;

    @Value("${spring.application.name}")
    private String appName;

    @Override
    public String createJWT(Authentication authentication) {


        Date now = new Date();
        return Jwts.builder().issuer(appName).subject("JWT_TOKEN")
                .claim("user_email", authentication.getName())
                .claim("user_authorities", authentication.getAuthorities().stream().map(
                        GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                .issuedAt(now)
                .expiration(new Date(now.getTime() + jwtExpire))
                .signWith(Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret))).compact();

    }

    @Override
    public Authentication validateJWT(String jwt) {

        Claims claims = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret)))
                .build().parseSignedClaims(jwt).getPayload();

        return new UsernamePasswordAuthenticationToken(claims.get("user_email").toString(), null,
                AuthorityUtils.commaSeparatedStringToAuthorityList(claims.get("user_authorities").toString()));
    }

}
