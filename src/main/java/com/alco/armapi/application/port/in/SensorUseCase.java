package com.alco.armapi.application.port.in;

import java.util.List;
import java.util.UUID;

import com.alco.armapi.domain.model.Sensor;
public interface SensorUseCase {
    Sensor saveSensor(Sensor sensor);

    Sensor getSensorById(UUID id);

    List<Sensor> getAllSensors();

    void deleteSensor(UUID id);

    Sensor updateSensor(UUID id, Sensor sensor);

    List<Sensor> getSensorsByDeviceId(UUID deviceId);

    List<Sensor> getSensorByStatus(String status);

    List<Sensor> getSensorByType(String type);

    List<Sensor> getSensorByName(String name);
}
