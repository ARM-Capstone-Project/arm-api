package com.alco.armapi.application.port.in;

import com.alco.armapi.domain.model.Sensor;

import java.util.List;

public interface SensorUseCase {
    Sensor saveSensor(Sensor sensor);

    Sensor getSensorById(String id);

    List<Sensor> getAllSensors();

    void deleteSensor(String id);

    Sensor updateSensor(String id, Sensor sensor);

    List<Sensor> getSensorByStatus(String status);

    List<Sensor> getSensorByType(String type);

    List<Sensor> getSensorByName(String name);
}