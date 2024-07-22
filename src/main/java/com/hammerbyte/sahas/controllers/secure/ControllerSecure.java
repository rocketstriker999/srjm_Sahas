package com.hammerbyte.sahas.controllers.secure;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api")
public class ControllerSecure {
    
    @PostAuthorize("hasAnyAuthority('FADMIN','HADMIN')")
    @GetMapping(path = "/fadmindata")
    public String fadmindata() {
        return "secure data fadmindata";
    }


    @PreAuthorize("hasAuthority('HADMIN')")
    @GetMapping(path = "/hadmindata")
    public String hadmindata() {
        return "secure data hadmindata";
    }


}
