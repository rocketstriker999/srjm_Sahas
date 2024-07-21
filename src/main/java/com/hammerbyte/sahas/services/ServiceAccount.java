package com.hammerbyte.sahas.services;


import com.hammerbyte.sahas.models.ModelUser;


public interface ServiceAccount {

    public ModelUser createAccount(ModelUser modelUser);
    public ModelUser getAccount(ModelUser modelUser);
}