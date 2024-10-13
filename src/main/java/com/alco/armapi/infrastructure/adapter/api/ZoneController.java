package com.alco.armapi.infrastructure.adapter.api;

import com.alco.armapi.application.port.in.ZoneUseCase;
import com.alco.armapi.domain.model.Zone;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/zones")
@RequiredArgsConstructor
public class ZoneController {

    @Autowired
    private ZoneUseCase zoneUseCase;

    @PostMapping
    public ResponseEntity<Zone> saveZone(@RequestBody Zone zone) {
        Zone createdZone = zoneUseCase.saveZone(zone);
        return ResponseEntity.ok(createdZone);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Zone> getZoneById(@PathVariable String id) {
        Zone zone = zoneUseCase.getZoneById(id);
        return ResponseEntity.ok(zone);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Zone> updateZone(@PathVariable String id, @RequestBody Zone zone) {
        Zone updatedZone = zoneUseCase.updateZone(id, zone);
        return ResponseEntity.ok(updatedZone);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZone(@PathVariable String id) {
        zoneUseCase.deleteZone(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Zone>> getAllZonesWithDevices() {
        List<Zone> zones = zoneUseCase.getAllZonesWithDevices();
        return ResponseEntity.ok(zones);
    }
}
