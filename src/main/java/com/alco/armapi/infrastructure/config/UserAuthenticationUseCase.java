package com.alco.armapi.infrastructure.config;

import com.alco.armapi.infrastructure.adapter.payload.request.LoginRequest;
import com.alco.armapi.infrastructure.adapter.payload.response.LoginResponse;

public interface UserAuthenticationUseCase {
    LoginResponse login(LoginRequest loginRequest);
//    String login(LoginRequest loginData);
}
