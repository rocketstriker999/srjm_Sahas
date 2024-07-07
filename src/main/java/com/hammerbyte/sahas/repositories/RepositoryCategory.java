package com.hammerbyte.sahas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hammerbyte.sahas.models.ModelCategory;

@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")
public interface RepositoryCategory extends JpaRepository<ModelCategory, Long> {

}
