package com.hammerbyte.sahas.controllers.open;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hammerbyte.sahas.models.ModelCategory;
import com.hammerbyte.sahas.services.ServiceCategory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@RestController
@RequestMapping("/template-details")
public class ControllerTemplate {

    @GetMapping("/hero-images")
    public ResponseEntity<List<String>> getHeroImages() {

        List<String> heroImages = new ArrayList<>();
        heroImages.add("https://placehold.co/100x100");
        heroImages.add("https://placehold.co/100x100");
        heroImages.add("https://placehold.co/100x100");
        heroImages.add("https://placehold.co/100x100");
        heroImages.add("https://placehold.co/100x100");
        heroImages.add("https://placehold.co/100x100");
        heroImages.add("https://placehold.co/100x100");

        return ResponseEntity.status(HttpStatus.OK).body(heroImages);
    }

    @GetMapping("/header-texts")
    public ResponseEntity<Map<String, String>> getTexts() {

        Map<String, String> texts = new HashMap<>();
        texts.put("header_title", "Sahas Smart Studies");
        texts.put("header_tagline", "Learn Digitally");
        texts.put("header_description",
                "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content.");
        return ResponseEntity.status(HttpStatus.OK).body(texts);
    }

    private ServiceCategory serviceCategory;

    @GetMapping("/showcase-products")
    public ResponseEntity<List<ModelCategory>> getProductsShowCase() {
        return ResponseEntity.status(HttpStatus.OK).body(serviceCategory.getAllCategories());
    }

    @GetMapping("/product-categories")
    public ResponseEntity<List<ModelCategory>> getProductCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(serviceCategory.getAllCategories());
    }

}
