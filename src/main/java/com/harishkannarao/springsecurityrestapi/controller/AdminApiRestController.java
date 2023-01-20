package com.harishkannarao.springsecurityrestapi.controller;

import com.harishkannarao.springsecurityrestapi.domain.UserData;
import com.harishkannarao.springsecurityrestapi.security.resolver.UserDataResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = {"/admin"})
public class AdminApiRestController {

    private final UserDataResolver userDataResolver;

    @Autowired
    public AdminApiRestController(UserDataResolver userDataResolver) {
        this.userDataResolver = userDataResolver;
    }

    @GetMapping(value = {"/get-user-data/{username}"})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserData> getUserData(@PathVariable("username") String username) {
        Optional<UserData> userData = userDataResolver.resolve(username);
        return userData.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
