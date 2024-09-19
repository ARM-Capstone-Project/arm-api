package com.alco.armapi.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Sensor {
    private String id;
    private String name;
    private String type;
    private String status;
    private Set<SensorReading> sensorReadings;  // A sensor can have multiple readings
}