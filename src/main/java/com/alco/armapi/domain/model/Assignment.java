package com.alco.armapi.domain.model;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Assignment {
    private String id;
    private User manager;
    private User operator;
}
