package com.alco.armapi.infrastructure.adapter.persistence.readings;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Readings {

    @DynamoDBAttribute
    private String sensor;             // Type of reading (temperature, humidity)
    @DynamoDBAttribute
    private String unit;             // Unit of measurement (Celsius, Fahrenheit, Percentage)
    @DynamoDBAttribute
    private double value;            // Value of the reading
}
