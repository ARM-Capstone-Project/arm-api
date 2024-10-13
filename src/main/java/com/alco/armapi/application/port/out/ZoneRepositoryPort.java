package com.alco.armapi.application.port.out;

import com.alco.armapi.domain.model.Zone;

import java.util.List;

public interface ZoneRepositoryPort {

    Zone saveZone(Zone zone);

    List<Zone> listZones();

    Zone getZoneById(String zoneId);

    void deleteZone(String id);

    Zone updateZone(String id, Zone zone);

    List<Zone> getAllZonesWithDevices();
}
