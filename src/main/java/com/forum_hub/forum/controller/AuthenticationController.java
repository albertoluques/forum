package com.forum_hub.forum.controller;

import com.forum_hub.forum.domain.user.User;
import com.forum_hub.forum.domain.user.UserAuth;
import com.forum_hub.forum.infra.security.JWTTokenData;

import com.forum_hub.forum.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity userAuth(@RequestBody @Valid UserAuth data) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var userAuth = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.tokenGeneration((User) userAuth.getPrincipal());
        return ResponseEntity.ok(new JWTTokenData(JWTtoken));
    }

}
