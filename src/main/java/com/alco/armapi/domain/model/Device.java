package com.alco.armapi.domain.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
public class Device {
    private UUID id;
    private String name;
    private String batchNo;
    private String description;
    private List<Sensor> sensors;  // A device can have multiple sensors
    private String type;
    private String location;
    private String tagNo;
    private String status;
    private List<User> users;
    //private Zone zone;
    private UUID zoneId;
}