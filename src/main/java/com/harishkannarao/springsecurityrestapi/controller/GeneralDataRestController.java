package com.harishkannarao.springsecurityrestapi.controller;

import com.harishkannarao.springsecurityrestapi.domain.GeneralData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/general-data"})
public class GeneralDataRestController {

    private static final Logger logger = LoggerFactory.getLogger(GeneralDataRestController.class);

    @GetMapping
    public ResponseEntity<GeneralData> getGeneralData() {
        logger.info("Received request for /general-data");
        return ResponseEntity.accepted().body(GeneralData.builder()
                .message("Welcome !!!")
                .build());
    }
}
