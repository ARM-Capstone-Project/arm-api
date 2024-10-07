package com.alco.armapi.application.port.out;

import com.alco.armapi.domain.model.Device;

import java.util.List;

public interface DeviceRepositoryPort {
    
    List<Device> listDevices();
    
    Device getDeviceById(String deviceId);
    
    Device saveDevice(Device device);
    
    void deleteDevice(String id);
    
    Device updateDevice(String id, Device device);
    
    List<Device> findDevicesByStatus(String status);
    
    List<Device> findDevicesByLocation(String location);
    
    List<Device> findDevicesByType(String type);
    
    List<Device> findDevicesByZoneId(String zoneId);
}
