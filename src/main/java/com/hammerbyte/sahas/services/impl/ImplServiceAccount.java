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
   
    @Override
    public ModelUser createAccount(ModelUser modelUser) {
        return repositoryUser.save(modelUser);       
    }

    @Override
    public ModelUser getAccount(ModelUser modelUser) {
        return repositoryUser.findByUserEmail(modelUser.getUserEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}