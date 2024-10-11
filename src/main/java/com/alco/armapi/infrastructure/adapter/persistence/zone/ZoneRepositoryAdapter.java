package com.alco.armapi.infrastructure.adapter.persistence.zone;

import com.alco.armapi.application.port.out.ZoneRepositoryPort;
import com.alco.armapi.common.PersistenceAdapter;
import com.alco.armapi.domain.model.Zone;
import com.alco.armapi.infrastructure.mapper.ZoneMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class ZoneRepositoryAdapter implements ZoneRepositoryPort {
    @Autowired
    private ZoneRepository zoneRepository;
    @Override
    public List<Zone> getAllZonesWithDevices() {
        List<ZoneEntity> zoneEntities = zoneRepository.findAll();
        return zoneEntities.stream()
                .map(ZoneMapper.INSTANCE::toDomainModel)
                .collect(Collectors.toList());
    }
}
