package com.alco.armapi.infrastructure.adapter.payload.request;
import jakarta.validation.constraints.Size;

public record SignupRequest(
        @Size(min = 3, max = 20)
        String username,

        @Size(max = 50)
        String email,

        @Size(min = 6, max = 20)
        String password
) {}