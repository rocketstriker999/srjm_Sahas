package com.hammerbyte.sahas.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOLogin {

    @NotEmpty(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String userEmail;

    @NotEmpty(message = "Password cannot be blank")
    @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters")
    private String userPassword;

    
}
