package com.alco.armapi.infrastructure.adapter.payload.request;

public record LoginRequest(
     String username,
     String password
){}
