package com.alco.armapi.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class Zone {
    private UUID id;
    private String name;
    private double latitude;
    private double longitude;
    private double radius;
    private Set<Device> devices;  // A zone can have multiple devices
    private Set<Device> users;
}