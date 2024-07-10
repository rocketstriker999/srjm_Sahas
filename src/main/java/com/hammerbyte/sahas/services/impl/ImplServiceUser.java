package com.hammerbyte.sahas.services.impl;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hammerbyte.sahas.models.ModelUser;
import com.hammerbyte.sahas.repositories.RepositoryUser;
import com.hammerbyte.sahas.services.ServiceUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Service
@AllArgsConstructor
@Getter
@Setter
public class ImplServiceUser implements ServiceUser {

    private RepositoryUser repositoryUser;

    @Override
    public Optional<ModelUser> findUserByEmail(String userEmail) {
        return repositoryUser.findByEmail(userEmail);
    }

    // being called by spring authentication
    @Override
    public UserDetails findSpringUserByEmail(String userEmail) {
        Optional<ModelUser> modelUser = findUserByEmail(userEmail);
        return modelUser.isPresent()
                ? User.withUsername(modelUser.get().getUserEmail()).password(modelUser.get().getUserPassword())
                        .roles(modelUser.get().getUserRole().name()).build()
                : null;

    }

}
