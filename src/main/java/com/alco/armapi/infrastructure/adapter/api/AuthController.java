package com.alco.armapi.infrastructure.adapter.api;

import com.alco.armapi.application.port.in.UserUseCase;
import com.alco.armapi.domain.model.User;
import com.alco.armapi.infrastructure.adapter.payload.request.LoginRequest;
import com.alco.armapi.infrastructure.adapter.payload.response.LoginResponse;
import com.alco.armapi.infrastructure.config.UserAuthenticationUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
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
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = authenticationUseCase.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) throws JsonProcessingException {
        User result = userUseCase.saveUser(user);
        return ResponseEntity.ok(result);
    }

}