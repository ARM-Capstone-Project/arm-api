package com.alco.armapi.infrastructure.adapter.api;


import com.alco.armapi.application.port.in.AdminUseCase;
import com.alco.armapi.application.port.in.UserUseCase;
import com.alco.armapi.common.Constants;
import com.alco.armapi.domain.model.User;
import com.alco.armapi.domain.model.Zone;

import com.alco.armapi.infrastructure.adapter.payload.request.AssignRoleRequest;
import com.alco.armapi.infrastructure.adapter.payload.request.LoginRequest;
import com.alco.armapi.infrastructure.adapter.persistence.user.UserEntity;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    AdminUseCase adminUseCase;

    @Autowired
    UserUseCase userUseCase;
    // Assign Role to User
    @PostMapping("/assign_role")
    @PreAuthorize("hasAuthority('"+ Constants.ROLE_ADMIN + "' )")
    public ResponseEntity<User> assignRoleToUser(@RequestParam String userId, @RequestParam String roleName) {
        User updatedUser = adminUseCase.assignRoleToUser(userId,roleName);
        return ResponseEntity.ok(updatedUser); // Return the updated user details
    }

    @PostMapping("/remove_role")
    @PreAuthorize("hasAuthority('"+ Constants.ROLE_ADMIN + "' )")
    public ResponseEntity<User> removeRoleFromUser(@RequestParam String userId, @RequestParam String roleName) {
        User updatedUser = adminUseCase.removeRoleFromUser(userId,roleName);
        return ResponseEntity.ok(updatedUser); // Return the updated user details
    }

    // Assign Zone to User
    @PostMapping("/assign_zone")
    @PreAuthorize("hasAuthority('"+ Constants.ROLE_MANAGER + "') or hasAuthority('"+ Constants.ROLE_ADMIN +"' )")
    public ResponseEntity<User> assignZoneToUser(@RequestParam String userId, @RequestParam UUID zoneId) {
        User updatedUser = adminUseCase.assignZoneToUser(userId, zoneId);
        return ResponseEntity.ok(updatedUser); // Return the updated user details
    }

    // Assign Device to Zone
    @PostMapping("/assign_device")
    @PreAuthorize("hasAuthority('"+ Constants.ROLE_MANAGER + "') or hasAuthority('"+ Constants.ROLE_ADMIN +"' )")
    public ResponseEntity<Zone> assignDeviceToZone(@RequestParam UUID zoneId, @RequestParam UUID deviceId) {
        Zone updatedZone = adminUseCase.assignDeviceToZone(zoneId, deviceId);
        return ResponseEntity.ok(updatedZone); // Return the updated zone details
    }

    @PostMapping("/assign")
    public ResponseEntity<?> assignOperatorToManager(@RequestParam String managerId, @RequestParam String operatorId) {
        adminUseCase.assignOperatorToManager(managerId, operatorId);

        // Step 2: Fetch the updated list of operators assigned to this manager
        List<User> assignedOperators = adminUseCase.getOperatorsByManagerId(managerId);

        return ResponseEntity.ok(assignedOperators);
    }

}