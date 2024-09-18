package com.alco.armapi.infrastructure.config;

import com.alco.armapi.infrastructure.adapter.payload.request.LoginRequest;

public interface UserAuthenticationUseCase {
    String login(LoginRequest loginData);
}
