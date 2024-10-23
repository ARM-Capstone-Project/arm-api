package com.alco.armapi.domain.model.readings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
//same format as Readings except values. Created new to segregate model
public class ReadingSensor {
    private String sensor;  // Type of reading (temperature, humidity)

    private String unit; // Unit of measurement (Celsius, Fahrenheit, Percentage)
}
