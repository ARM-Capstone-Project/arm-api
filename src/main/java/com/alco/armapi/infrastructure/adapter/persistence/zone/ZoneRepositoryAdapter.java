package com.alco.armapi.infrastructure.adapter.persistence.zone;

import com.alco.armapi.application.port.out.ZoneRepositoryPort;
import com.alco.armapi.common.PersistenceAdapter;
import com.alco.armapi.domain.model.Zone;
import com.alco.armapi.infrastructure.mapper.ZoneMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;
import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class ZoneRepositoryAdapter implements ZoneRepositoryPort {

    @Autowired
    private ZoneRepository zoneRepository;

    @Override
    public Zone saveZone(Zone zone) {
        ZoneEntity zoneEntity = createZoneEntity(zone);
        ZoneEntity savedZoneEntity = zoneRepository.save(zoneEntity);
        return ZoneMapper.INSTANCE.toDomainModel(savedZoneEntity);
    }

    @Override
    public List<Zone> listZones() {
        List<ZoneEntity> zoneEntities = zoneRepository.findAll();
        return zoneEntities.stream()
                .map(ZoneMapper.INSTANCE::toDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public Zone getZoneById(UUID zoneId) {
        ZoneEntity zoneEntity = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new RuntimeException("Zone not found with id: " + zoneId));
        return ZoneMapper.INSTANCE.toDomainModel(zoneEntity);
    }

    @Override
    public void deleteZone(UUID id) {
        zoneRepository.deleteById(id);
    }

    @Override
    public Zone updateZone(UUID id, Zone zone) {
        ZoneEntity existingZoneEntity = zoneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Zone not found with id: " + id));

        existingZoneEntity.setName(isNotEmpty(zone.getName()) ? zone.getName() : existingZoneEntity.getName());
        existingZoneEntity.setLatitude(isValidDouble(zone.getLatitude()) ? zone.getLatitude() : existingZoneEntity.getLatitude());
        existingZoneEntity.setLongitude(isValidDouble(zone.getLongitude()) ? zone.getLongitude() : existingZoneEntity.getLongitude());
        existingZoneEntity.setRadius(isValidDouble(zone.getRadius()) ? zone.getRadius() : existingZoneEntity.getRadius());

        ZoneEntity updatedZoneEntity = zoneRepository.save(existingZoneEntity);
        return ZoneMapper.INSTANCE.toDomainModel(updatedZoneEntity);
    }

    @Override
    public List<Zone> getAllZonesWithDevices() {
        List<ZoneEntity> zoneEntities = zoneRepository.findAll();
        return zoneEntities.stream()
                .map(ZoneMapper.INSTANCE::toDomainModel)
                .collect(Collectors.toList());
    }

    private ZoneEntity createZoneEntity(Zone zone) {
        ZoneEntity zoneEntity = new ZoneEntity();
        zoneEntity.setName(zone.getName());
        zoneEntity.setLatitude(zone.getLatitude());
        zoneEntity.setLongitude(zone.getLongitude());
        zoneEntity.setRadius(zone.getRadius());

        return zoneEntity;
    }

    private boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    private boolean isValidDouble(double value) {
        return value != 0.0;
    }
}