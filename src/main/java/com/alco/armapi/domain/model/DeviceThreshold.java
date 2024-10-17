package com.alco.armapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceThreshold {
    private String id;
    private String deviceId;
    private String sensorId;
    private String reading;
    private String condition;
    private String email;
    private String level;
    private String unit;
}
