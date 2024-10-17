package com.alco.armapi.application.service;

import com.alco.armapi.application.port.in.ZoneUseCase;
import com.alco.armapi.application.port.out.ZoneRepositoryPort;
import com.alco.armapi.common.UseCase;
import com.alco.armapi.domain.model.Zone;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@UseCase
@Transactional
@RequiredArgsConstructor
public class ZoneService implements ZoneUseCase {
    private final ZoneRepositoryPort zoneRepositoryPort;

    @Override
    public Zone saveZone(Zone zone) {
        return zoneRepositoryPort.saveZone(zone);
    }

    @Override
    public List<Zone> getAllZonesWithDevices() {
        return zoneRepositoryPort.getAllZonesWithDevices();
    }

    @Override

    public Zone getZoneById(UUID id) {

        return zoneRepositoryPort.getZoneById(id);
    }

    @Override
    public Zone updateZone(UUID id, Zone zone) {

        return zoneRepositoryPort.updateZone(id, zone);
    }

    @Override

    public void deleteZone(UUID id) {
        zoneRepositoryPort.deleteZone(id);
    }
}
