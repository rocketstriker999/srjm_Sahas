package com.hammerbyte.sahas.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hammerbyte.sahas.models.ModelUser;

public interface RepositoryUser extends JpaRepository<ModelUser,String>{

    Optional<ModelUser> findByUserEmail(String userEmail);

}
