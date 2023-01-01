package com.harishkannarao.springsecurityrestapi.controller;

import com.harishkannarao.springsecurityrestapi.domain.SensitiveData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/sensitive-data"})
public class SensitiveDataRestController {

    @GetMapping
    public ResponseEntity<SensitiveData> getSensitiveData() {
        return ResponseEntity.ok().body(SensitiveData.builder()
                .firstName("secretFirstName")
                .lastName("secretLastName")
                .build());
    }
}
