package com.alco.armapi.application.port.in;
import com.alco.armapi.domain.model.Device;
import java.util.List;
import java.util.UUID;
import java.util.Optional;;
public interface DeviceUseCase {
    Device saveDevice(Device device);

    Optional<Device> getDeviceById(UUID id);

    List<Device> getAllDevices();

    void deleteDevice(UUID id);

    Device updateDevice(UUID id, Device device);

    List<Device> getDeviceByStatus(String status);

    List<Device> getDeviceByLocation(String location);

    List<Device> getDeviceByType(String type);

    List<Device> getDeviceByZoneId(UUID zoneId);

    Device getDeviceByTagNo(String tagNo);
}
