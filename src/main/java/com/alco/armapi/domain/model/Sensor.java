package com.alco.armapi.domain.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Sensor {
    private String id;
    private String name;
    private String type;
    private String status;
    private List<SensorReading> sensorReadings;  // A sensor can have multiple readings
    private Device device;
    private String unit;
}