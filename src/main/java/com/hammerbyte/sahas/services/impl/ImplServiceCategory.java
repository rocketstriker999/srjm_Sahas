package com.hammerbyte.sahas.services.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.hammerbyte.sahas.models.ModelCategory;
import com.hammerbyte.sahas.repositories.RepositoryCategory;
import com.hammerbyte.sahas.services.ServiceCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Service
@AllArgsConstructor
@Getter
@Setter
public class ImplServiceCategory implements ServiceCategory{

    private RepositoryCategory repositoryCategory;

    @Override
    public List<ModelCategory> getAllCategories() {
        return repositoryCategory.findAll();
    }
    
}
