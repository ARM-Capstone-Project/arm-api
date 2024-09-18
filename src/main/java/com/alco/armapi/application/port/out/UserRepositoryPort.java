package com.alco.armapi.application.port.out;

import com.alco.armapi.domain.model.User;

import java.util.List;

public interface UserRepositoryPort {

    List<User> listUsers();

    User getUserById(String userId);

    User saveUser(User user);

    void deleteUser(String id);

    User updateUser(String id, User user);
}
