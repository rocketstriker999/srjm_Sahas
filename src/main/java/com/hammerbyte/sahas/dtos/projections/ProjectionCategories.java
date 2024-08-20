package com.hammerbyte.sahas.dtos.projections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;


public interface ProjectionCategories {


    

    Long getCategoryId();
    String getCategoryName();
    String getCategoryPhoto();
}
