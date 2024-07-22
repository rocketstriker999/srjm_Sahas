package com.hammerbyte.sahas.controllers.open;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hammerbyte.sahas.dtos.DTOLogin;
import com.hammerbyte.sahas.dtos.DTOSignup;
import com.hammerbyte.sahas.models.ModelUser;
import com.hammerbyte.sahas.services.ServiceAccount;
import com.hammerbyte.sahas.services.ServiceJWT;
import com.hammerbyte.sahas.services.ServiceUser;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.core.Authentication;

@AllArgsConstructor
@Getter
@Setter
@RestController
@RequestMapping("/account")
public class ControllerAccount {

    private ServiceAccount serviceAccount;
    private ServiceUser serviceUser;

    private ServiceJWT serviceJWT;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;


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
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dtoLogin.getUserEmail(), dtoLogin.getUserPassword()));
            if (authentication.isAuthenticated()) {
                responseBody.put("token", serviceJWT.createJWT(authentication));
                responseBody.put("user", authentication.getPrincipal());
                return ResponseEntity.status(HttpStatus.OK).body(responseBody);
            } else {
                throw new UsernameNotFoundException("Invalid Credentials");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
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

            if(serviceUser.findByUserEmail(modelUser.getUserEmail()).isPresent()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Already Exist");
            }else{
                serviceAccount.createAccount(modelUser);
                return ResponseEntity.status(HttpStatus.CREATED).body("User Created");
            }

           
        }
    }

}
