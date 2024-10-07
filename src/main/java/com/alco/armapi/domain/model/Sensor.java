package com.alco.armapi.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Sensor {
    private UUID id;
    private String name;
    private String type;
    private String status;
    private List<SensorReading> sensorReadings;  // A sensor can have multiple readings
    private UUID deviceId;
}