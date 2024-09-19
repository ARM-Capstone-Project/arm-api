package com.alco.armapi.application.port.in;

import com.alco.armapi.domain.model.Zone;

import java.util.List;

public interface ZoneUseCase {
    List<Zone> getAllZonesWithDevices();
}
