package com.alco.armapi.application.port.out;

import java.util.List;
import java.util.UUID;

import com.alco.armapi.domain.model.Sensor;
public interface SensorRepositoryPort {
    List<Sensor> listSensors();
    
    Sensor getSensorById(UUID sensorId);
    
    Sensor saveSensor(Sensor sensor);
    
    void deleteSensor(UUID id);
    
    Sensor updateSensor(UUID id, Sensor sensor);
    
    List<Sensor> findSensorsByDeviceId(UUID deviceId);

    List<Sensor> findSensorsByStatus(String status);
    
    List<Sensor> findSensorsByType(String type);

    List<Sensor> findSensorsByName(String name);
}
