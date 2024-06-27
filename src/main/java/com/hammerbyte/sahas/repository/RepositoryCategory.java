package com.hammerbyte.sahas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.hammerbyte.sahas.model.ModelCategory;

@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")
public interface RepositoryCategory extends JpaRepository<ModelCategory, Long> {

}
