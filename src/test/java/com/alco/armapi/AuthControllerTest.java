package com.alco.armapi;

import org.springframework.boot.test.context.SpringBootTest;
import com.alco.armapi.application.port.in.UserUseCase;
import com.alco.armapi.domain.model.User;
import com.alco.armapi.infrastructure.adapter.payload.request.LoginRequest;
import com.alco.armapi.infrastructure.adapter.payload.response.LoginResponse;
import com.alco.armapi.infrastructure.config.UserAuthenticationUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.util.List;

import com.alco.armapi.infrastructure.adapter.api.AuthController;

@SpringBootTest
class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private UserAuthenticationUseCase authenticationUseCase;

    @Mock
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_ShouldReturnLoginResponse() {
        LoginRequest loginRequest = new LoginRequest("testUser", "testPassword");
        
        LoginResponse expectedResponse = new LoginResponse("token", "testUser", "test@example.com", List.of("ROLE_USER"));

        when(authenticationUseCase.login(any(LoginRequest.class))).thenReturn(expectedResponse);

        ResponseEntity<LoginResponse> response = authController.login(loginRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        verify(authenticationUseCase, times(1)).login(loginRequest);
    }

    @Test
    void saveUser_ShouldReturnSavedUser() throws JsonProcessingException {
        User user = new User();
        user.setUsername("testUser");
        User savedUser = new User();
        savedUser.setUsername("testUser");

        when(userUseCase.saveUser(any(User.class))).thenReturn(savedUser);

        ResponseEntity<User> response = authController.saveUser(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(savedUser, response.getBody());
        verify(userUseCase, times(1)).saveUser(user);
    }
}