package com.hammerbyte.sahas.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hammerbyte.sahas.models.ModelUser;

public interface RepositoryUser extends JpaRepository<ModelUser,String>{
 
    
   

    @Query("SELECT u FROM ModelUser u WHERE u.userEmail = :userEmail")
    Optional<ModelUser> findByEmail(@Param("userEmail") String userEmail);

}
