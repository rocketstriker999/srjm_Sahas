package com.hammerbyte.sahas.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.hammerbyte.sahas.models.ModelUser;
import com.hammerbyte.sahas.repositories.RepositoryUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Component
public class ImplUserDetailsService implements UserDetailsService {

    private RepositoryUser repositoryUser;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<ModelUser> modelUser = repositoryUser.findByUserEmail(userEmail);
        if (modelUser.isPresent()) {
            return modelUser.get();
        } else {
            throw new UsernameNotFoundException("Invalid Credentials");
        }
    }

}
