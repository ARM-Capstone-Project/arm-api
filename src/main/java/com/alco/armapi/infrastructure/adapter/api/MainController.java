package com.alco.armapi.infrastructure.adapter.api;


import com.alco.armapi.common.Constants;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/main")
public class MainController {

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content " ;
    }


    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('"+ Constants.ROLE_ADMIN + "' )")
    public String adminAccess() {
        return "Admin Board.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('"+ Constants.ROLE_USER + "') or hasAuthority('"+ Constants.ROLE_ADMIN + "' )")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/manager")
    @PreAuthorize("hasAuthority('"+ Constants.ROLE_MANAGER + "') or hasAuthority('"+ Constants.ROLE_ADMIN +"' )")
    public String moderatorAccess() {
        return "Manager Board.";
    }

}
