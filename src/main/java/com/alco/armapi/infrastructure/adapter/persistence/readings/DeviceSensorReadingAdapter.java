package com.alco.armapi.infrastructure.adapter.persistence.readings;

import com.alco.armapi.application.port.out.DeviceSensorReadingRepositoryPort;
import com.alco.armapi.common.PersistenceAdapter;
import com.alco.armapi.domain.model.readings.DeviceSensorReading;
import com.alco.armapi.domain.model.readings.ReadingDevice;
import com.alco.armapi.infrastructure.mapper.DeviceSensorReadingMapper;
import com.alco.armapi.infrastructure.mapper.ReadingDeviceMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class DeviceSensorReadingAdapter  implements DeviceSensorReadingRepositoryPort {
    private final DeviceSensorReadingRepository deviceSensorReadingRepository;
    @Override
    public void save(DeviceSensorReading deviceSensorReading) {
        deviceSensorReadingRepository.save(DeviceSensorReadingMapper.INSTANCE.toEntity(deviceSensorReading));
    }

    @Override
    public List<DeviceSensorReading> findAll() {
        return deviceSensorReadingRepository.findAll().stream()
                .map(DeviceSensorReadingMapper.INSTANCE::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(DeviceSensorReading deviceSensorReading) {
        deviceSensorReadingRepository.delete(DeviceSensorReadingMapper.INSTANCE.toEntity(deviceSensorReading));
    }

    @Override
    public void deleteAll() {
        deviceSensorReadingRepository.deleteAll();
    }

    @Override
    public List<DeviceSensorReading> findByDeviceId(String deviceId) {
        return deviceSensorReadingRepository.findByDeviceId(deviceId).stream()
                .map(DeviceSensorReadingMapper.INSTANCE::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public void saveList(List<DeviceSensorReading> deviceSensorReadings) {;
        deviceSensorReadingRepository.batchSave(deviceSensorReadings.stream().map(DeviceSensorReadingMapper.INSTANCE::toEntity)
                .collect(Collectors.toList()));
    }

    public List<ReadingDevice> getDeviceFromReading(){
        return deviceSensorReadingRepository.getDeviceFromReading().stream()
                .map(ReadingDeviceMapper.INSTANCE::toDomainModel)
                .collect(Collectors.toList());
    };
}
