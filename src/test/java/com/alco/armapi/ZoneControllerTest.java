package com.alco.armapi;

import com.alco.armapi.application.port.in.ZoneUseCase;
import com.alco.armapi.domain.model.Zone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.alco.armapi.infrastructure.adapter.api.ZoneController;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ZoneControllerTest {

    @InjectMocks
    private ZoneController zoneController;

    @Mock
    private ZoneUseCase zoneUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveZone_ShouldReturnCreatedZone() {
        Zone zone = new Zone();
        when(zoneUseCase.saveZone(any(Zone.class))).thenReturn(zone);

        ResponseEntity<Zone> response = zoneController.saveZone(zone);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(zone, response.getBody());
        verify(zoneUseCase, times(1)).saveZone(zone);
    }

    @Test
    void getZoneById_ShouldReturnZone() {
        UUID zoneId = UUID.randomUUID();
        Zone zone = new Zone();
        when(zoneUseCase.getZoneById(zoneId)).thenReturn(zone);

        ResponseEntity<Zone> response = zoneController.getZoneById(zoneId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(zone, response.getBody());
        verify(zoneUseCase, times(1)).getZoneById(zoneId);
    }

    @Test
    void updateZone_ShouldReturnUpdatedZone() {
        UUID zoneId = UUID.randomUUID();
        Zone zone = new Zone();
        when(zoneUseCase.updateZone(eq(zoneId), any(Zone.class))).thenReturn(zone);

        ResponseEntity<Zone> response = zoneController.updateZone(zoneId, zone);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(zone, response.getBody());
        verify(zoneUseCase, times(1)).updateZone(zoneId, zone);
    }

    @Test
    void deleteZone_ShouldReturnNoContent() {
        UUID zoneId = UUID.randomUUID();
        doNothing().when(zoneUseCase).deleteZone(zoneId);

        ResponseEntity<Void> response = zoneController.deleteZone(zoneId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(zoneUseCase, times(1)).deleteZone(zoneId);
    }

    @Test
    void getAllZonesWithDevices_ShouldReturnZoneList() {
        Zone zone = new Zone();
        List<Zone> zones = Collections.singletonList(zone);
        when(zoneUseCase.getAllZonesWithDevices()).thenReturn(zones);

        ResponseEntity<List<Zone>> response = zoneController.getAllZonesWithDevices();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(zones, response.getBody());
        verify(zoneUseCase, times(1)).getAllZonesWithDevices();
    }
}