package com.alco.armapi.application.service;

import java.util.List;
import com.alco.armapi.common.UseCase;
import com.alco.armapi.application.port.in.DeviceUseCase;
import com.alco.armapi.application.port.out.DeviceRepositoryPort;
import com.alco.armapi.domain.model.Device;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.UUID;
import java.util.Optional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class DeviceService implements DeviceUseCase{
    private final DeviceRepositoryPort deviceRepositoryPort;

    @Override
    public Device saveDevice(Device device){
        return deviceRepositoryPort.saveDevice(device);
    };

    @Override
    public Optional<Device> getDeviceById(UUID id){
        return deviceRepositoryPort.getDeviceById(id);
    };

    @Override
    public List<Device> getAllDevices(){
        return deviceRepositoryPort.listDevices();
    };

    @Override
    public void deleteDevice(UUID id){
        deviceRepositoryPort.deleteDevice(id);
    };

    @Override
    public Device updateDevice(UUID id, Device device){
        return deviceRepositoryPort.updateDevice(id, device);
    };

    @Override
    public List<Device> getDeviceByStatus(String status){
        return deviceRepositoryPort.findDevicesByStatus(status);
    };

    @Override
    public List<Device> getDeviceByLocation(String location){
        return deviceRepositoryPort.findDevicesByLocation(location);
    };

    @Override
    public List<Device> getDeviceByType(String type){
        return deviceRepositoryPort.findDevicesByLocation(type);
    };

    @Override
    public List<Device> getDeviceByZoneId(UUID zoneId){
        return deviceRepositoryPort.findDevicesByZoneId(zoneId);
    };

    @Override
    public Device getDeviceByTagNo(String tagNo){
        return deviceRepositoryPort.getDeviceByTagNo(tagNo);
    };
}