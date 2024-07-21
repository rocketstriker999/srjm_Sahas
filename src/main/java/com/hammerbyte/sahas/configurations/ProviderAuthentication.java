package com.hammerbyte.sahas.configurations;

import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.hammerbyte.sahas.models.ModelUser;
import com.hammerbyte.sahas.repositories.RepositoryUser;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProviderAuthentication implements AuthenticationProvider {

    @NonNull
    private RepositoryUser repositoryUser;
    @NonNull
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Get the user and password from authentication object

        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        // Fetch the data from db with customerRepository
        Optional<ModelUser> user = repositoryUser.findByUserEmail(username);
        // If there is at least one element in the list
        if (user.isPresent()) {
            // Check if the given password when hashed with password encoder is the same as
            // the user's
            // password from the db
            if (passwordEncoder.matches(pwd, user.get().getUserPassword())) {
                return new UsernamePasswordAuthenticationToken(user.get().getUserEmail(), user.get().getUserPassword(),
                        List.of(new SimpleGrantedAuthority(user.get().getUserRole().name())));
            } else {
                // The password did not match
                throw new BadCredentialsException("Invalid Password");
            }
        } else {
            // There was no user with the given username in the database
            throw new BadCredentialsException("No user registered with this details");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (ProviderAuthentication.class.isAssignableFrom(authentication));
    }

}
