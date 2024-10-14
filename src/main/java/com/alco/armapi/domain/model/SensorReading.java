package com.alco.armapi.domain.model;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
public class SensorReading {
    private UUID id;
    private LocalDateTime timestamp;
    private double value;
    private String unit;
    private UUID sensorId;
}