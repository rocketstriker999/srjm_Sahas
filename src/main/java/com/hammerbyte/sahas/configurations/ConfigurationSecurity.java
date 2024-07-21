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
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

import com.hammerbyte.sahas.enums.EnumUserRole;
import com.hammerbyte.sahas.models.ModelUser;
import com.hammerbyte.sahas.repositories.RepositoryUser;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.List;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class ConfigurationSecurity {

    
    @NonNull
    private RepositoryUser repositoryUser;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // disable csrf for api requests
        httpSecurity.csrf(csrf -> csrf.disable())
        // allow cors
        .cors(cors -> cors.disable())
        // disable basic auth with form submission only jwt will be allowed
        .httpBasic(AbstractHttpConfigurer::disable)
        .formLogin(AbstractHttpConfigurer::disable)
        // apply path security
        .authorizeHttpRequests(auth ->
        // remove security for /account path
            auth
                    .requestMatchers("/account/**").permitAll()
                    .requestMatchers("/api/**").hasRole(EnumUserRole.FADMIN.name())
                    .anyRequest().authenticated()
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
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return new ProviderManager(daoAuthenticationProvider);
    }

    // This is going to be called when user tries to login
    // automatically decoded value of ID from JWT will be inserted in it
    // It will try to look if we really have such user in db
    @Bean
    public UserDetailsService userDetailsService() {
        return userEmail -> {
            Optional<ModelUser> modelUser = repositoryUser.findByUserEmail(userEmail);
            if (modelUser.isPresent()) {
                return new User(modelUser.get().getUserEmail(), modelUser.get().getUserPassword(),List.of(new SimpleGrantedAuthority(modelUser.get().getUserRole().name())));
            } else {
                throw new UsernameNotFoundException("Invalid Credentials");
            }
        };
    }
    

}
