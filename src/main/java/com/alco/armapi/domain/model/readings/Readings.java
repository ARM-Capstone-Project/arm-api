package com.alco.armapi.domain.model.readings;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Readings {


    private String sensor;             // Type of reading (temperature, humidity)

    private String unit;             // Unit of measurement (Celsius, Fahrenheit, Percentage)

    private double value;            // Value of the reading
}
