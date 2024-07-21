package com.hammerbyte.sahas.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.hammerbyte.sahas.enums.EnumUserRole;
import com.hammerbyte.sahas.filters.FilterAfterAuthentication;
import com.hammerbyte.sahas.filters.FilterAtAuthentication;
import com.hammerbyte.sahas.filters.JWTTokenGeneratorFilter;
import com.hammerbyte.sahas.filters.JWTTokenValidatorFilter;
import com.hammerbyte.sahas.services.ServiceJWT;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.NonNull;


@RequiredArgsConstructor
@Getter
@Setter
@Configuration
@EnableWebSecurity
public class ConfigurationSecurity {


    @Value("${app.jwt.header}")
    private String jwtHeader;

    @NonNull
    private ServiceJWT serviceJWT;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // disable csrf for api requests
        httpSecurity.csrf(csrf -> csrf.disable())
                // allow cors
                .cors(cors -> cors.disable())
                // disable basic auth with form submission only jwt will be allowed
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .addFilterAt(new FilterAtAuthentication(), BasicAuthenticationFilter.class)
                .addFilterAfter(new FilterAfterAuthentication(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenGeneratorFilter(jwtHeader,serviceJWT), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidatorFilter(jwtHeader,serviceJWT), BasicAuthenticationFilter.class)
                // apply path security
                .authorizeHttpRequests(auth ->
                // remove security for /account path
                        auth    
                        .requestMatchers("/api/**").hasAuthority(EnumUserRole.HADMIN.name())
                        .requestMatchers("/account/**").permitAll()
                // auth.requestMatchers("/f admin/**").hasAnyRole(EnumUserRole.FADMIN.name(),
                // EnumUserRole.HADMIN.name());
                // keep security for all other requests
                // auth.anyRequest().authenticated();
                )
                // ask to use oauth2 Resource Server with JWT encoder and decoder inbuilt

                // make sessions stateless
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // add exception handling

        // build and return the Security Filter Chain
        return httpSecurity.build();
    }

    // return default Authentication Manager for single usage
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
                ProviderAuthentication authenticationProvider =
                new ProviderAuthentication(userDetailsService, passwordEncoder);
        ProviderManager providerManager = new ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);
        return  providerManager;
    }

  

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

     @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }

}
