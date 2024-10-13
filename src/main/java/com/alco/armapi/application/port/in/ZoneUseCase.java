package com.alco.armapi.application.port.in;

import com.alco.armapi.domain.model.Zone;
import java.util.List;

public interface ZoneUseCase {
    Zone saveZone(Zone zone);

    List<Zone> getAllZonesWithDevices();

    Zone getZoneById(String id);

    Zone updateZone(String id, Zone zone);

    void deleteZone(String id);
}
