package com.alco.armapi.application.port.out;

import com.alco.armapi.domain.model.Device;

import java.util.List;
import java.util.UUID;

public interface DeviceRepositoryPort {

    // Method to list all devices
    List<Device> listDevices();
    
    Device getDeviceById(UUID deviceId);
    
    Device saveDevice(Device device);
    
    void deleteDevice(UUID id);
    
    Device updateDevice(UUID id, Device device);
    
    List<Device> findDevicesByStatus(String status);
    
    List<Device> findDevicesByLocation(String location);
    
    List<Device> findDevicesByType(String type);
    
    List<Device> findDevicesByZoneId(UUID zoneId);
}