package com.alco.armapi;

import com.alco.armapi.application.port.in.UserUseCase;
import com.alco.armapi.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest;
import com.alco.armapi.infrastructure.adapter.api.UserController;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void viewUser_ShouldReturnUser() {
        String userId = "user123";
        User user = new User();
        user.setId(userId);
        when(userUseCase.getUserById(userId)).thenReturn(user);

        ResponseEntity<User> response = userController.viewUser(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userUseCase, times(1)).getUserById(userId);
    }

    @Test
    void viewAllUsers_ShouldReturnUserList() {
        User user = new User();
        List<User> users = Collections.singletonList(user);
        when(userUseCase.getAllUsers()).thenReturn(users);

        ResponseEntity<List<User>> response = userController.viewAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
        verify(userUseCase, times(1)).getAllUsers();
    }

    @Test
    void deleteUser_ShouldReturnNoContent() {
        String userId = "user123";
        doNothing().when(userUseCase).deleteUser(userId);

        ResponseEntity<Void> response = userController.deleteUser(userId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userUseCase, times(1)).deleteUser(userId);
    }

    @Test
    void updateUser_ShouldReturnUpdatedUser() {
        String userId = "user123";
        User user = new User();
        user.setId(userId);
        when(userUseCase.updateUser(anyString(), any(User.class))).thenReturn(user);

        ResponseEntity<User> response = userController.updateUser(userId, user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userUseCase, times(1)).updateUser(userId, user);
    }
}