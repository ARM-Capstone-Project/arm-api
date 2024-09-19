package com.alco.armapi.infrastructure.adapter.payload.request;

public record AssignRoleRequest (
        String userId,
        String roleName
){}