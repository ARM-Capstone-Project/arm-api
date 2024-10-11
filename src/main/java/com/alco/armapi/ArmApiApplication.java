package com.alco.armapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.alco.armapi")
public class ArmApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArmApiApplication.class, args);
    }

}
