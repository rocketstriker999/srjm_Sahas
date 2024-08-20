package com.hammerbyte.sahas.services;

import java.util.List;
import java.util.Set;

import com.hammerbyte.sahas.dtos.projections.ProjectionCategories;
import com.hammerbyte.sahas.models.ModelCategory;

public interface ServiceCategory {

    public List<ModelCategory> getAllCategories();

    public Set<ProjectionCategories> getProjectionCategories();

    
} 
