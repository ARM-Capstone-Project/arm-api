package com.alco.armapi.util;



import com.alco.armapi.domain.model.readings.DeviceSensorReading;
import com.alco.armapi.domain.model.readings.Readings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class DHT22DataGenerator {


    public static final String TEMPERATURE = "temperature";
    public static final String CELSIUS = "celsius";
    public static final String FAHRENHEIT = "fahrenheit";
    public static final String PERCENTAGE = "percentage";
    public static final String HUMIDITY = "humidity";
    public static final String DHT_22_001 = "DHT22-001";

    public static List<DeviceSensorReading> get() {


        // Starting date
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.OCTOBER, 5, 0, 0); // Start from Oct 5, 2024

        List<DeviceSensorReading> deviceSensorReadings = new ArrayList<>();

        // Generate data for 7 days (168 hours)
        for (int i = 0; i < 168; i++) {
            double tempCelsius = 25 + Math.random() * 10; // Random temperature between 25 and 35
            double tempFahrenheit = (tempCelsius * 9 / 5) + 32; // Convert to Fahrenheit
            double humidity = 50 + Math.random() * 20; // Random humidity between 50 and 70


            deviceSensorReadings.add(DeviceSensorReading.builder()
                    .deviceId(DHT_22_001).
                    readings(Arrays.asList(
                            Readings.builder().sensor(TEMPERATURE).value(tempCelsius).unit(CELSIUS).build(),
                            Readings.builder().sensor(TEMPERATURE).value(tempFahrenheit).unit(FAHRENHEIT).build(),
                            Readings.builder().sensor(HUMIDITY).value(humidity).unit(PERCENTAGE).build()
                    ))
                    .timestamp(calendar.getTime())
                    .build());
            // Move to the next hour
            calendar.add(Calendar.HOUR, 1);


        }

        return deviceSensorReadings;


    }
}
