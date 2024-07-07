package com.hammerbyte.sahas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import com.hammerbyte.sahas.models.ModelUser;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.Instant;


@Service
@RequiredArgsConstructor
public class ServiceJWT {

    @lombok.NonNull
    private JwtEncoder jwtEncoder;

    @Value("${app.jwt.issuer}")
    private String jwtIssuer;

    @Value("${app.jwt.expire}")
    private Long jwtExpire;

    public String createJWT(ModelUser modelUser) {

     

        Instant now = Instant.now();
        return jwtEncoder.encode(JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS256).build(),
                JwtClaimsSet.builder()
                        .issuer(jwtIssuer)
                        .issuedAt(now)
                        .expiresAt(now.plusSeconds(jwtExpire))
                        .subject("needtoenteridhere")
                        .claim("role", modelUser.getUserRole())
                        .build()))
                .getTokenValue();
    }

}
