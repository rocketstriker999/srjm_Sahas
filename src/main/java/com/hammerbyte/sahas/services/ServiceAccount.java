package com.hammerbyte.sahas.services;


import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.hammerbyte.sahas.dtos.projections.ProjectionTestiMonies;
import com.hammerbyte.sahas.models.ModelUser;


public interface ServiceAccount {

    public ModelUser createAccount(ModelUser modelUser);
    public ModelUser getAccount(ModelUser modelUser);
    public Set<ModelUser> getAccounts(Integer count);
    public Slice<ProjectionTestiMonies> getTestiMonies(Pageable paging);

}