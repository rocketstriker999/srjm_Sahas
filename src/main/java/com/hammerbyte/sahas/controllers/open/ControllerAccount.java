package com.hammerbyte.sahas.controllers.open;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hammerbyte.sahas.configurations.ConfigurationSecurity;
import com.hammerbyte.sahas.dtos.DTOSignup;
import com.hammerbyte.sahas.models.ModelUser;
import com.hammerbyte.sahas.services.ServiceAccount;
import com.hammerbyte.sahas.services.ServiceJWT;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@AllArgsConstructor
@RestController
@RequestMapping("/account")
public class ControllerAccount {

    private ServiceAccount serviceAccount;
    private ServiceJWT serviceJWT;
    private BCryptPasswordEncoder passwordEncoder;

    // user can send email and password to authenticate and get jwt token back
    @PostMapping("/login")
    public String login() {
        return "logged in";
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@Valid @RequestBody DTOSignup dtoSignup,BindingResult bindingResult) {

        HashMap<String,Object> responseBody = new HashMap<>();

        if(bindingResult.hasErrors()){
            for(ObjectError objectError:bindingResult.getAllErrors())
                responseBody.put(objectError.getObjectName(), objectError.getDefaultMessage());
            return ResponseEntity.badRequest().body(responseBody) ;
        }
        else{
            ModelUser modelUser = new ModelUser();
            modelUser.setUserEmail(dtoSignup.getUserEmail());
            modelUser.setUserPassword(passwordEncoder.encode(dtoSignup.getUserPassword()));
            modelUser.setUserName(dtoSignup.getUserName());
            responseBody.put("token", serviceJWT.createJWT(modelUser));
            responseBody.put("user",serviceAccount.createAccount(modelUser));
            return ResponseEntity.ok(responseBody) ;
        }



    }

}
