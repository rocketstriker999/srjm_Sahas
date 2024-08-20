package com.hammerbyte.sahas.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hammerbyte.sahas.models.ModelProduct;
import com.hammerbyte.sahas.models.ModelPurchase;
import com.hammerbyte.sahas.models.ModelUser;
import com.hammerbyte.sahas.repositories.RepositoryPurchase;
import com.hammerbyte.sahas.repositories.RepositoryUser;
import com.hammerbyte.sahas.services.ServiceUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Service
@AllArgsConstructor
@Getter
@Setter
public class ImplServiceUser implements ServiceUser {

    private RepositoryUser repositoryUser;
    private RepositoryPurchase repositoryPurchase;


    @Override
    public Optional<ModelUser> findByUserEmail(String userEmail) {
        return repositoryUser.findByUserEmail(userEmail);
    }


}
