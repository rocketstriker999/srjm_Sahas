package com.hammerbyte.sahas.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DTOSignup {

    @NotEmpty
    private String userName;

    @NotEmpty
    private String userEmail;

    @NotEmpty
    private String userPassword;
    
}
