package com.hammerbyte.sahas.services.impl;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hammerbyte.sahas.models.ModelUser;
import com.hammerbyte.sahas.repositories.RepositoryUser;
import com.hammerbyte.sahas.services.ServiceUser;

public class ImplServiceUser implements ServiceUser {

    private RepositoryUser repositoryUser;

    @Override
    public ModelUser findUserById(String userId) {

        return repositoryUser.findById(userId).get();

    }

    @Override
    public ModelUser findUserByEmail(String userEmail) {

        // need to impelment frthure

        return null;

    }

    //being called by spring authentication
    @Override
    public UserDetails findSpringUserById(String userId) throws UsernameNotFoundException {
        Optional<ModelUser> modelUser = repositoryUser.findById(userId);

        if (modelUser.isPresent()) {
            return User.withUsername(modelUser.get().getUserName()).password(modelUser.get().getUserPassword())
                    .roles(modelUser.get().getUserRole().name()).build();
        } else {
            throw new UsernameNotFoundException("User Not Found");
        }

    }

}
