package com.alco.armapi.application.service;

import com.alco.armapi.common.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.UUID;
import com.alco.armapi.application.port.in.SensorUseCase;
import com.alco.armapi.application.port.out.SensorRepositoryPort;
import com.alco.armapi.domain.model.Sensor;

@UseCase
@Transactional
@RequiredArgsConstructor
public class SensorService implements SensorUseCase{
    private final SensorRepositoryPort sensorRepositoryPort;

    @Override
    public Sensor saveSensor(Sensor sensor){
        return sensorRepositoryPort.saveSensor(sensor);
    }

    @Override
    public Sensor getSensorById(UUID id){
        return sensorRepositoryPort.getSensorById(id);
    };

    @Override
    public List<Sensor> getAllSensors(){
        return sensorRepositoryPort.listSensors();
    };

    @Override
    public void deleteSensor(UUID id){
        sensorRepositoryPort.deleteSensor(id);
    };

    @Override
    public Sensor updateSensor(UUID id, Sensor sensor){
        return sensorRepositoryPort.updateSensor(id, sensor);
    };

    @Override
    public List<Sensor> getSensorsByDeviceId(UUID deviceId){
        return sensorRepositoryPort.findSensorsByDeviceId(deviceId);
    };
    @Override
    public List<Sensor> getSensorByStatus(String status){
        return sensorRepositoryPort.findSensorsByStatus(status);
    };

    @Override
    public List<Sensor> getSensorByType(String type){
        return sensorRepositoryPort.findSensorsByType(type);
    };

    @Override
    public List<Sensor> getSensorByName(String name){
        return sensorRepositoryPort.findSensorsByName(name);
    };
}
