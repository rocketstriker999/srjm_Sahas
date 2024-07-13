package com.hammerbyte.sahas.configurations;

import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.hammerbyte.sahas.enums.EnumUserRole;
import com.hammerbyte.sahas.services.ServiceUser;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class ConfigurationSecurity {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @NonNull
    private ServiceUser serviceUser;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // disable csrf for api requests
        httpSecurity.csrf(csrf -> csrf.disable());
        // apply path security
        httpSecurity.authorizeHttpRequests(auth -> {
            // remove security for /account path
            auth.requestMatchers("/account/**").permitAll();
            //add Admin path
            auth.requestMatchers("/hadmin/**").hasRole(EnumUserRole.HADMIN.name());
            auth.requestMatchers("/fadmin/**").hasAnyRole(EnumUserRole.FADMIN.name(),EnumUserRole.HADMIN.name());

            // keep security for all other requests
            auth.anyRequest().authenticated();
        });
        // ask to use oauth2 Resource Server with JWT encoder and decoder inbuilt
        httpSecurity.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
        // make sessions stateless
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // build and return the Security Filter Chain
        return httpSecurity.build();
    }

    // return default Authentication Manager for single usage
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(daoAuthenticationProvider);
    }

    // This is going to be called when user tries to login
    // automatically decoded value of ID from JWT will be inserted in it
    // It will try to look if we really have such user in db
    @Bean
    public UserDetailsService userDetailsService() {
        return userEmail -> serviceUser.findSpringUserByEmail(userEmail);
    }

    // register a single bean as password encoder in case of signup from user
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withSecretKey(new SecretKeySpec(jwtSecret.getBytes(), ""))
                .macAlgorithm(MacAlgorithm.HS256).build();
    }

    // Single Bean JWT Encoder Used to Encode JWT
    @Bean
    public JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(new ImmutableSecret<>(jwtSecret.getBytes()));
    }

}
