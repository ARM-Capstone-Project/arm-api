package com.alco.armapi.infrastructure.adapter.api;


import com.alco.armapi.application.port.in.AdminUseCase;
import com.alco.armapi.common.Constants;
import com.alco.armapi.domain.model.User;
import com.alco.armapi.domain.model.Zone;
import com.alco.armapi.infrastructure.adapter.payload.request.AssignRoleRequest;
import com.alco.armapi.infrastructure.adapter.payload.request.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    AdminUseCase adminUseCase;

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
    public ResponseEntity<User> assignZoneToUser(@RequestParam String userId, @RequestParam String zoneId) {
        User updatedUser = adminUseCase.assignZoneToUser(userId, zoneId);
        return ResponseEntity.ok(updatedUser); // Return the updated user details
    }

    // Assign Device to Zone
    @PostMapping("/assign_device")
    @PreAuthorize("hasAuthority('"+ Constants.ROLE_MANAGER + "') or hasAuthority('"+ Constants.ROLE_ADMIN +"' )")
    public ResponseEntity<Zone> assignDeviceToZone(@RequestParam String zoneId, @RequestParam String deviceId) {
        Zone updatedZone = adminUseCase.assignDeviceToZone(zoneId, deviceId);
        return ResponseEntity.ok(updatedZone); // Return the updated zone details
    }

}