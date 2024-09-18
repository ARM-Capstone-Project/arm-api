package com.alco.armapi.infrastructure.adapter.payload.request;
import com.alco.armapi.domain.model.Role;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Size;

import java.util.Set;
public record SignupRequest(
        @Size(min = 3, max = 20)
        String username,

        @Size(max = 50)
        String email,

        @Size(min = 6, max = 20)
        String password
) {}