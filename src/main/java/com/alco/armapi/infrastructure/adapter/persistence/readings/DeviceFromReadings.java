package com.alco.armapi.infrastructure.adapter.persistence.readings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
//to get distinct device and associated sensors from AWS devices' readings
public class DeviceFromReadings {
    private String id;
    private String deviceId;
    private List<Readings> readings;
}