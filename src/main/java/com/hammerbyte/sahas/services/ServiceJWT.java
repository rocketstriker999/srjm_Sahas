package com.hammerbyte.sahas.services;

import com.hammerbyte.sahas.models.ModelUser;

public interface ServiceJWT {

    public String createJWT(ModelUser modelUser);
} 