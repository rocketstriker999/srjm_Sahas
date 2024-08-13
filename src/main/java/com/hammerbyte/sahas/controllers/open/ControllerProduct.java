package com.hammerbyte.sahas.controllers.open;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hammerbyte.sahas.services.ServiceCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Getter
@Setter
@RestController
@RequestMapping("/template")
public class ControllerProduct {

    private ServiceCategory ServiceCategory;

    @GetMapping("/details")
    public ResponseEntity<HashMap<String, Object>> getTemplateDetails() {

        HashMap<String, Object> responseBody = new HashMap<>();

        responseBody.put("images", "img1.png");

        responseBody.put("data", ServiceCategory.getAllCategories());

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);

    }
}
