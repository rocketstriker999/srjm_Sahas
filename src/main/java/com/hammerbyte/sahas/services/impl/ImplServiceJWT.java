package com.hammerbyte.sahas.services.impl;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import com.hammerbyte.sahas.models.ModelUser;
import com.hammerbyte.sahas.services.ServiceJWT;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Service
@RequiredArgsConstructor
@Getter
@Setter
public class ImplServiceJWT implements ServiceJWT {

   

    

    @Override
    public String createJWT(ModelUser modelUser) {

        Instant now = Instant.now();
        return jwtEncoder.encode(JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS256).build(),
                JwtClaimsSet.builder()
                        .issuer(jwtIssuer)
                        .issuedAt(now)
                        .expiresAt(now.plusSeconds(jwtExpire))
                        .subject(modelUser.getUserEmail())
                        .claim("role", modelUser.getUserRole().name())
                        .build()))
                .getTokenValue();
    }
    
}
