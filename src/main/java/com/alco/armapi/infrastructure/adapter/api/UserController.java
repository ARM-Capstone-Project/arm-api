package com.alco.armapi.infrastructure.adapter.api;

import com.alco.armapi.application.port.in.UserUseCase;
import com.alco.armapi.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserUseCase userUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<User> viewUser(@PathVariable String id) {
        User user = userUseCase.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("")
    public ResponseEntity<List<User>> viewAllUsers() {
        List<User> users = userUseCase.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userUseCase.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        System.out.println("Update User ID: " + id);
        User updatedUser = userUseCase.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }
}
