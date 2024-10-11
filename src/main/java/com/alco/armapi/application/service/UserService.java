package com.alco.armapi.application.service;

import com.alco.armapi.application.port.in.UserUseCase;
import com.alco.armapi.application.port.out.UserRepositoryPort;
import com.alco.armapi.common.UseCase;
import com.alco.armapi.domain.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@Transactional
@RequiredArgsConstructor
public class UserService implements UserUseCase {
    private final UserRepositoryPort userRepositoryPort;
    
    @Override
    public User saveUser(User user) {
        return userRepositoryPort.saveUser(user);
    }

    @Override
    public User getUserById(String id) {
        return userRepositoryPort.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepositoryPort.listUsers();
    }

    @Override
    public void deleteUser(String id) {
        userRepositoryPort.deleteUser(id);
    }

    @Override
    public User updateUser(String id, User user) {
        return userRepositoryPort.updateUser(id, user);
    }


}
