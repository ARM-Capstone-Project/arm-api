package com.alco.armapi.application.port.out;

import com.alco.armapi.domain.model.Zone;

import java.util.List;

public interface ZoneRepositoryPort {
    List<Zone> getAllZonesWithDevices();
}
