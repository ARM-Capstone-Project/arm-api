package com.alco.armapi.infrastructure.adapter.api;

import com.alco.armapi.application.port.in.UserUseCase;
import com.alco.armapi.domain.model.User;
import com.alco.armapi.infrastructure.adapter.payload.request.LoginRequest;
import com.alco.armapi.infrastructure.adapter.payload.response.AuthResponse;
import com.alco.armapi.infrastructure.config.UserAuthenticationUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserAuthenticationUseCase authenticationUseCase;

    @Autowired
    UserUseCase userUseCase;

    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        String token = authenticationUseCase.login(loginRequest);
        AuthResponse authResponse = new AuthResponse(token);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) throws JsonProcessingException {
        User result = userUseCase.saveUser(user);
        return ResponseEntity.ok(result);
    }

}