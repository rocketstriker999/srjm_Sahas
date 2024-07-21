package com.hammerbyte.sahas.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

import com.hammerbyte.sahas.models.ModelUser;

public interface ServiceUser {

    public Optional<ModelUser> findByUserEmail(String userEmail);

}
