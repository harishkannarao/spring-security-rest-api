package com.harishkannarao.springsecurityrestapi.controller;

import com.harishkannarao.springsecurityrestapi.domain.UserData;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = {"/user-data"})
public class UserDataRestController {

    @GetMapping
    public ResponseEntity<UserData> getUserData(@RequestAttribute("authentication") Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        Optional<UserData> userData = Optional.empty();
        if ("user-name-1".equals(principal.getUsername())) {
            userData = Optional.of(UserData.builder()
                    .firstName("userFirstName")
                    .lastName("userLastName")
                    .build());
        }
        return userData.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
