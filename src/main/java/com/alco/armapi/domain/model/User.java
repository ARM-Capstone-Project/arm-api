package com.alco.armapi.domain.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class User {
    private String id;
    private String username;
    private String password;
    private String email;
    private List<Role> roles;
    private List<Zone> zones;
}
