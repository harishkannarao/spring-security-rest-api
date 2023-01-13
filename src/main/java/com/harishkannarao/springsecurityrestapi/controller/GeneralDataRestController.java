package com.harishkannarao.springsecurityrestapi.controller;

import com.harishkannarao.springsecurityrestapi.domain.GeneralData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/general-data"})
public class GeneralDataRestController {

    @GetMapping
    public ResponseEntity<GeneralData> getGeneralData() {
        return ResponseEntity.ok().body(GeneralData.builder()
                .message("Welcome !!!")
                .build());
    }
}
