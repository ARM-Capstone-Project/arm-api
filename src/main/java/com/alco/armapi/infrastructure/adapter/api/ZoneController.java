package com.alco.armapi.infrastructure.adapter.api;

import com.alco.armapi.application.port.in.ZoneUseCase;
import com.alco.armapi.domain.model.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    @Autowired
    private ZoneUseCase zoneUseCase;

    @GetMapping
    public ResponseEntity<List<Zone>> getAllZonesWithDevices() {
        List<Zone> zones = zoneUseCase.getAllZonesWithDevices();
        return ResponseEntity.ok(zones);
    }
}