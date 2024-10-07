package com.alco.armapi.application.port.out;

import com.alco.armapi.domain.model.Sensor;

import java.util.List;

public interface SensorRepositoryPort {
    
    List<Sensor> listSensors();
    
    Sensor getSensorById(String sensorId);
    
    Sensor saveSensor(Sensor sensor);
    
    void deleteSensor(String id);
    
    Sensor updateSensor(String id, Sensor sensor);
    
    List<Sensor> findSensorsByStatus(String status);
    
    List<Sensor> findSensorsByType(String type);

    List<Sensor> findSensorsByName(String name);
}