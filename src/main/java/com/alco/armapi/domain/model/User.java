package com.alco.armapi.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;
@Getter
@Setter
public class User {
    private String id;
    private String username;
    private String password;
    private String email;
    private Set<Role> roles;
    private Set<Zone> zones;
}
