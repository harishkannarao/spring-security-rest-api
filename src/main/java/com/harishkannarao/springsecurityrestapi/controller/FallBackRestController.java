package com.harishkannarao.springsecurityrestapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(path = {"*"})
@Slf4j
public class FallBackRestController {

    @RequestMapping(method = {GET, PUT, POST, DELETE, OPTIONS, PATCH, TRACE})
    public ResponseEntity<Void> notFound() {
        return ResponseEntity.notFound().build();
    }
}
