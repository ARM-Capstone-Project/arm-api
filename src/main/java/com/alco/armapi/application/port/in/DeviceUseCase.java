package com.alco.armapi.application.port.in;

import com.alco.armapi.domain.model.Device;
import java.util.List;

public interface DeviceUseCase {
    
    Device saveDevice(Device device);

    Device getDeviceById(String id);

    List<Device> getAllDevices();

    void deleteDevice(String id);

    Device updateDevice(String id, Device device);

    List<Device> getDeviceByStatus(String status);

    List<Device> getDeviceByLocation(String location);

    List<Device> getDeviceByType(String type);

    List<Device> getDeviceByZoneId(String zoneId);
}