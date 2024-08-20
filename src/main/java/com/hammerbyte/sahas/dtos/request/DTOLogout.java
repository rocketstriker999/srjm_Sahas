package com.hammerbyte.sahas.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOLogout {

   
    @NotEmpty(message = "JWT cannot be blank")
    private String userJWT;

   

}
