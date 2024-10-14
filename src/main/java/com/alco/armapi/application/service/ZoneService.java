package com.alco.armapi.application.service;

import com.alco.armapi.application.port.in.ZoneUseCase;
import com.alco.armapi.application.port.out.ZoneRepositoryPort;
import com.alco.armapi.common.UseCase;
import com.alco.armapi.domain.model.Zone;
import jakarta.transaction.Transactional;
import java.util.List;

@UseCase
@Transactional
public class ZoneService implements ZoneUseCase {
    private final ZoneRepositoryPort zoneRepositoryPort;

    public ZoneService(ZoneRepositoryPort zoneRepositoryPort) {
        this.zoneRepositoryPort = zoneRepositoryPort;
    }

    @Override
    public List<Zone> getAllZonesWithDevices() {
        return zoneRepositoryPort.getAllZonesWithDevices();
    }
}
