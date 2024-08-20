package com.hammerbyte.sahas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hammerbyte.sahas.models.ModelProduct;


public interface RepositoryProduct extends JpaRepository<ModelProduct, Long> {



}
