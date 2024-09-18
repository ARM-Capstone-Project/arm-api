package com.alco.armapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Role {
    private String id;
    private String name;
    private String description;
}
