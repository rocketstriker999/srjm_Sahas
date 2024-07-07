package com.hammerbyte.sahas.controllers.secure;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api")
public class ControllerSecure {
    

    @GetMapping
    public String data() {
        return "secure data";
    }

}
