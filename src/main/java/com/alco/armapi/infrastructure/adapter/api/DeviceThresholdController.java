package com.alco.armapi.infrastructure.adapter.api;

import com.alco.armapi.application.port.in.DeviceThresholdUseCase;
import com.alco.armapi.application.service.DeviceThresholdService;
import com.alco.armapi.domain.model.DeviceThreshold;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/device-thresholds")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DeviceThresholdController {
    private final DeviceThresholdUseCase deviceThresholdService;

    @PostMapping()
    public ResponseEntity<String> createDeviceThreshold(@RequestBody DeviceThreshold deviceThreshold) {
        log.info("Creating device threshold: {}", deviceThreshold);
        deviceThresholdService.save(deviceThreshold);
        return ResponseEntity.ok("DeviceThreshold saved successfully");
    }

    @GetMapping()
    public ResponseEntity<List<DeviceThreshold>> getAllDeviceThresholds() {
        log.info("Fetching all device thresholds");
        List<DeviceThreshold> thresholds = deviceThresholdService.findAll();
        return ResponseEntity.ok(thresholds);
    }

    @GetMapping("/by-device-id/{deviceId}")
    public ResponseEntity<List<DeviceThreshold>> getByDeviceId(@PathVariable String deviceId) {
        log.info("Fetching device thresholds for deviceId: {}", deviceId);
        List<DeviceThreshold> thresholds = deviceThresholdService.findByDeviceId(deviceId);
        return ResponseEntity.ok(thresholds);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDeviceThreshold(@PathVariable String id) {
        log.info("Deleting device threshold with ID: {}", id);

        DeviceThreshold deviceThreshold = new DeviceThreshold();
        deviceThreshold.setId (id); // Ensure your DeviceThreshold model has the appropriate method to set the ID

        deviceThresholdService.delete(deviceThreshold);
        return ResponseEntity.ok("DeviceThreshold deleted successfully");
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAllDeviceThresholds() {
        log.info("Deleting all device thresholds");
        deviceThresholdService.deleteAll();
        return ResponseEntity.ok("All DeviceThresholds deleted successfully");
    }

}
