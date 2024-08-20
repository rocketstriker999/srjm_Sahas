package com.hammerbyte.sahas.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hammerbyte.sahas.dtos.projections.ProjectionTestiMonies;
import com.hammerbyte.sahas.models.ModelUser;

public interface RepositoryUser extends JpaRepository<ModelUser, String> {

    Optional<ModelUser> findByUserEmail(String userEmail);

    @Query("SELECT users.userName AS userName, users.userPurchases AS userPurchases " +
            "FROM ModelUser users")
    Slice<ProjectionTestiMonies> getProjectionTestiMonies(Pageable pageable);

}
