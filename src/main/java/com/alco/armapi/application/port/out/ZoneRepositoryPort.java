package com.alco.armapi.application.port.out;

import com.alco.armapi.domain.model.Zone;

import java.util.List;
import java.util.UUID;

public interface ZoneRepositoryPort {
    Zone saveZone(Zone zone);

    List<Zone> listZones();

    Zone getZoneById(UUID zoneId);

    void deleteZone(UUID id);

    Zone updateZone(UUID id, Zone zone);

    List<Zone> getAllZonesWithDevices();
}
