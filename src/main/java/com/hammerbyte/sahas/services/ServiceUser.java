package com.hammerbyte.sahas.services;

import java.util.Optional;

import com.hammerbyte.sahas.models.ModelProduct;
import com.hammerbyte.sahas.models.ModelUser;

public interface ServiceUser {

    public Optional<ModelUser> findByUserEmail(String userEmail);

    

}
