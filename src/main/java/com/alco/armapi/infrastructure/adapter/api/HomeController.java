package com.alco.armapi.infrastructure.adapter.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("")
    public String homePage(Model model) {
        // Add any attributes needed for the home page
        model.addAttribute("appName", "MyApp");
        return "home";
    }

}