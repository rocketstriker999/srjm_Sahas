package com.hammerbyte.sahas.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hammerbyte.sahas.models.ModelUser;
import com.hammerbyte.sahas.repositories.RepositoryUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Service
public class ServiceAccount {

    private RepositoryUser repositoryUser;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    

    public ModelUser createAccount(ModelUser modelUser) {
        return repositoryUser.save(modelUser);       
    }

    public ModelUser authenticate(ModelUser modelUser) {
        return repositoryUser.findById(modelUser.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
    }

}
