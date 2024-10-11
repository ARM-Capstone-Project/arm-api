package com.alco.armapi.util;



import com.alco.armapi.domain.model.readings.DeviceSensorReading;
import com.alco.armapi.domain.model.readings.Readings;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class MethaneDataGenerator {


    public static final String METHANE = "methane";
    public static final String PPM = "ppm";
    public static final String AC_11_0856 = "ac11-0856";

    public static List<DeviceSensorReading> get() {


        // Starting date
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.OCTOBER, 5, 0, 0); // Start from Oct 5, 2024

        List<DeviceSensorReading> deviceSensorReadings = new ArrayList<>();

        // Generate data for 7 days (168 hours)
        for (int i = 0; i < 168; i++) {

            double methane = 0 + Math.random() * 5; // Random methane value between 0 and 5 ppm

            deviceSensorReadings.add(DeviceSensorReading.builder()
                    .deviceId(AC_11_0856).
                    readings(Collections.singletonList(Readings.builder().sensor(METHANE).value(methane).unit(PPM).build()))
                    .timestamp(calendar.getTime())
                    .build());
            // Move to the next hour
            calendar.add(Calendar.HOUR, 1);


        }

        return deviceSensorReadings;


    }
}
