package com.alco.armapi.application.port.in;

import com.alco.armapi.domain.model.Zone;
import java.util.List;
import java.util.UUID;

public interface ZoneUseCase {
    Zone saveZone(Zone zone);

    List<Zone> getAllZonesWithDevices();


    Zone getZoneById(UUID id);

    Zone updateZone(UUID id, Zone zone);

    void deleteZone(UUID id);

}
