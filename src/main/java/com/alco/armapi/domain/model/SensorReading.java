package com.alco.armapi.domain.model;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SensorReading {
    private String id;
    private LocalDateTime timestamp;
    private double value;
    private String unit;
}