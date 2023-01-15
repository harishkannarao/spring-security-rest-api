package com.harishkannarao.springsecurityrestapi.controller.testendpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/test-endpoint"})
public class TestEndpointRestController {

    @GetMapping
    public ResponseEntity<Void> success() {
        return ResponseEntity.noContent().build();
    }
}
