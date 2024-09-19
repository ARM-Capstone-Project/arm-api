package com.alco.armapi.application.port.in;

import com.alco.armapi.common.UseCase;
import com.alco.armapi.domain.model.User;
import java.util.List;

public interface UserUseCase {
    User saveUser(User user);

    User getUserById(String id);

    List<User> getAllUsers();

    void deleteUser(String id);

    User updateUser(String id, User user);
}
