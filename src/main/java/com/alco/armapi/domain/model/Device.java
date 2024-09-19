package com.alco.armapi.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Device {
    private String id;
    private String name;
    private String batchNo;
    private String description;
    private Set<Sensor> sensors;  // A device can have multiple sensors
}