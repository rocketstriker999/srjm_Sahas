package com.hammerbyte.sahas.services.impl;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hammerbyte.sahas.models.ModelUser;
import com.hammerbyte.sahas.repositories.RepositoryUser;
import com.hammerbyte.sahas.services.ServiceAccount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Service
@AllArgsConstructor
@Getter
@Setter
public class ImplServiceAccount implements ServiceAccount{

    private RepositoryUser repositoryUser;
    // private PasswordEncoder passwordEncoder;
    // private AuthenticationManager authenticationManager;
    
    @Override
    public ModelUser createAccount(ModelUser modelUser) {
        return repositoryUser.save(modelUser);       
    }

    @Override
    public ModelUser authenticate(ModelUser modelUser) {
        return repositoryUser.findById(modelUser.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}