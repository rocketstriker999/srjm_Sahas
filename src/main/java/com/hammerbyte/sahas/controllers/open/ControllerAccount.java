package com.hammerbyte.sahas.controllers.open;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hammerbyte.sahas.dtos.DTOLogin;
import com.hammerbyte.sahas.dtos.DTOSignup;
import com.hammerbyte.sahas.models.ModelUser;
import com.hammerbyte.sahas.services.ServiceAccount;
import com.hammerbyte.sahas.services.ServiceJWT;
import com.hammerbyte.sahas.services.ServiceUser;
import com.nimbusds.jwt.JWT;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/account")
public class ControllerAccount {

    private ServiceAccount serviceAccount;
    private ServiceUser serviceUser;

    private ServiceJWT serviceJWT;
    private BCryptPasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

     // user can send email and password to authenticate and get jwt token back
     @PostMapping("/logout")
     public ResponseEntity<Object> logout(@Valid @RequestBody DTOSignup dtoSignup, BindingResult bindingResult){

        HashMap<String, Object> responseBody = new HashMap<>();


        if (bindingResult.hasErrors()) {

            for (ObjectError objectError : bindingResult.getAllErrors())
                responseBody.put(objectError.getObjectName(), objectError.getDefaultMessage());
            return ResponseEntity.badRequest().body(responseBody);
        }else{
            
            //destroyWT token from authorization header 

        }


     }

    // user can send email and password to authenticate and get jwt token back
    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody DTOLogin dtoLogin, BindingResult bindingResult) {

        HashMap<String, Object> responseBody = new HashMap<>();

        if (bindingResult.hasErrors()) {

            for (ObjectError objectError : bindingResult.getAllErrors())
                responseBody.put(objectError.getObjectName(), objectError.getDefaultMessage());
            return ResponseEntity.badRequest().body(responseBody);
        }

        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dtoLogin.getUserEmail(), dtoLogin.getUserPassword())
            );

            ModelUser modelUser = serviceUser.findUserByEmail(dtoLogin.getUserEmail()).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
            );

            responseBody.put("token", serviceJWT.createJWT(modelUser));
            responseBody.put("user", serviceAccount.authenticate(modelUser));
            return ResponseEntity.ok(responseBody);

        } catch (Exception e) {
            e.printStackTrace();
            responseBody.put("error", "Invalid email or password");
            return ResponseEntity.status(401).body(responseBody);
        }

    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@Valid @RequestBody DTOSignup dtoSignup, BindingResult bindingResult) {

        HashMap<String, Object> responseBody = new HashMap<>();

        if (bindingResult.hasErrors()) {
            for (ObjectError objectError : bindingResult.getAllErrors())
                responseBody.put(objectError.getObjectName(), objectError.getDefaultMessage());
            return ResponseEntity.badRequest().body(responseBody);
        } else {
            ModelUser modelUser = new ModelUser();
            modelUser.setUserEmail(dtoSignup.getUserEmail());
            modelUser.setUserPassword(passwordEncoder.encode(dtoSignup.getUserPassword()));
            modelUser.setUserName(dtoSignup.getUserName());
            responseBody.put("token", serviceJWT.createJWT(modelUser));
            responseBody.put("user", serviceAccount.createAccount(modelUser));
            return ResponseEntity.ok(responseBody);
        }
    }

   
}
