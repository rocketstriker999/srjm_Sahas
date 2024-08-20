package com.hammerbyte.sahas.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hammerbyte.sahas.dtos.projections.ProjectionCategories;
import com.hammerbyte.sahas.models.ModelCategory;

public interface RepositoryCategory extends JpaRepository<ModelCategory, Long> {

    @Query("SELECT categories.categoryId , categories.categoryName , categories.categoryPhoto  " +
           "FROM ModelCategory categories WHERE categories.categoryVisible = true")
    Set<ProjectionCategories> getProjectionCategories();

}
