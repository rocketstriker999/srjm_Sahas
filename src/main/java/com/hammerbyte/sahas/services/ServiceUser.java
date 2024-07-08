package com.hammerbyte.sahas.services;

import org.springframework.security.core.userdetails.UserDetails;

import com.hammerbyte.sahas.models.ModelUser;

public interface ServiceUser {

    public ModelUser findUserById(String userId);
    public ModelUser findUserByEmail(String userEmail);
    public UserDetails findSpringUserById(String userId);

}
