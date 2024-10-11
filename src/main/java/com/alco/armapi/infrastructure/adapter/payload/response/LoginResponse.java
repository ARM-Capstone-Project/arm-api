package com.alco.armapi.infrastructure.adapter.payload.response;

import java.util.List;

public record LoginResponse(
        String token,
        String username,
        String email,
        List<String> roles
) {}
