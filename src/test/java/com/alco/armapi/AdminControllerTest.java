package com.alco.armapi;

import org.springframework.boot.test.context.SpringBootTest;

import com.alco.armapi.application.port.in.AdminUseCase;
import com.alco.armapi.application.port.in.UserUseCase;
import com.alco.armapi.application.port.in.ZoneUseCase;
import com.alco.armapi.domain.model.User;
import com.alco.armapi.domain.model.Zone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import com.alco.armapi.infrastructure.adapter.api.AdminController;
import com.alco.armapi.infrastructure.adapter.persistence.assignment.AssignmentEntity;
import com.alco.armapi.infrastructure.adapter.persistence.user.UserRepository;
import com.alco.armapi.infrastructure.adapter.persistence.zone.ZoneRepository;

@SpringBootTest
class AdminControllerTest {

    @InjectMocks
    private AdminController adminController;

    @Mock
    private AdminUseCase adminUseCase;

    @Mock
    private UserUseCase userUseCase;

    @Mock
    private ZoneUseCase zoneUseCase;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ZoneRepository zoneRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void assignRoleToUser_ShouldReturnUpdatedUser() {
        String userId = "userId";
        String roleName = "ROLE_USER";
        User updatedUser = new User();
        updatedUser.setUsername("testUser");

        when(adminUseCase.assignRoleToUser(userId, roleName)).thenReturn(updatedUser);

        ResponseEntity<User> response = adminController.assignRoleToUser(userId, roleName);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUser, response.getBody());
        verify(adminUseCase, times(1)).assignRoleToUser(userId, roleName);
    }

    @Test
    void removeRoleFromUser_ShouldReturnUpdatedUser() {
        String userId = "userId";
        String roleName = "ROLE_USER";
        User updatedUser = new User();
        updatedUser.setUsername("testUser");

        when(adminUseCase.removeRoleFromUser(userId, roleName)).thenReturn(updatedUser);

        ResponseEntity<User> response = adminController.removeRoleFromUser(userId, roleName);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUser, response.getBody());
        verify(adminUseCase, times(1)).removeRoleFromUser(userId, roleName);
    }

@Test
void assignZoneToUser_ShouldReturnUpdatedUser() {
    String userId = "userId";
    UUID zoneId = UUID.randomUUID();

    User user = new User();
    user.setId(userId);
    user.setUsername("testUser");
    
    Zone zone = new Zone();
    zone.setId(zoneId);
    
    List<Zone> zones = new ArrayList<>();
    user.setZones(zones);
    
    
    when(userUseCase.getUserById(userId)).thenReturn(user);
    when(adminUseCase.assignZoneToUser(userId, zoneId)).thenReturn(user);
    
    ResponseEntity<User> response = adminController.assignZoneToUser(userId, zoneId);
    
    assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody(), "Response body should not be null"); // Ensure body is not null
        assertEquals("testUser", response.getBody().getUsername());
        
    //verify(userUseCase, times(1)).getUserById(userId); //unable to verify since only using in RepositoryAdapter
    verify(adminUseCase, times(1)).assignZoneToUser(userId, zoneId);
}

    @Test
    void assignDeviceToZone_ShouldReturnUpdatedZone() {
        UUID zoneId = UUID.randomUUID();
        UUID deviceId = UUID.randomUUID();
        Zone updatedZone = new Zone();
        updatedZone.setId(zoneId);

        when(adminUseCase.assignDeviceToZone(zoneId, deviceId)).thenReturn(updatedZone);

        ResponseEntity<Zone> response = adminController.assignDeviceToZone(zoneId, deviceId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedZone, response.getBody());
        verify(adminUseCase, times(1)).assignDeviceToZone(zoneId, deviceId);
    }

    @Test
void assignOperatorToManager_ShouldReturnAssignedOperators() {
    String managerId = "managerId";
    String operatorId = "operatorId";
    
    User assignedUser = new User();
    assignedUser.setUsername("assignedOperator");
    
    List<User> assignedOperators = Collections.singletonList(assignedUser);

AssignmentEntity mockAssignment = new AssignmentEntity();
when(adminUseCase.assignOperatorToManager(managerId, operatorId)).thenReturn(mockAssignment);

    when(adminUseCase.getOperatorsByManagerId(managerId)).thenReturn(assignedOperators);
    
    ResponseEntity<?> response = adminController.assignOperatorToManager(managerId, operatorId);
    
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(assignedOperators, response.getBody());
    
    verify(adminUseCase, times(1)).assignOperatorToManager(managerId, operatorId);
    verify(adminUseCase, times(1)).getOperatorsByManagerId(managerId);
}

}