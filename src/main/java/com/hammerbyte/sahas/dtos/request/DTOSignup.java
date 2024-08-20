package com.hammerbyte.sahas.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOSignup {

   
    @NotEmpty(message = "UserName cannot be blank")
    @Size(min = 8, message = "UserName Too Short")
    private String userName;

    @NotEmpty(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String userEmail;

    @NotEmpty(message = "Password cannot be blank")
    @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters")
    private String userPassword;

}
