package com.alco.armapi.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Device {
    private UUID id;
    private String name;
    private String batchNo;
    private String description;
    private List<Sensor> sensors;  // A device can have multiple sensors
    private String type;
    private String location;
    private double latitude;
    private double longitude;
    private String status;
    private Zone zone;
    private List<User> users; // A device can have multiple users assigned
}