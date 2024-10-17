package com.alco.armapi.domain.model;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;
import java.util.List;

@Getter
@Setter
public class Zone {
    private UUID id;
    private String name;
    private double latitude;
    private double longitude;
    private double radius;
    private List<Device> devices;  // A zone can have multiple devices
}