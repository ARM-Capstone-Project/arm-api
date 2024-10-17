package com.alco.armapi.infrastructure.adapter.persistence.zone;

import com.alco.armapi.common.AuditableEntity;
import com.alco.armapi.infrastructure.adapter.persistence.device.DeviceEntity;
import com.alco.armapi.infrastructure.adapter.persistence.user.UserEntity;
import jakarta.persistence.*;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)  // Include superclass, AuditableEntity fields
@Entity
@Table(name = "zones")
public class ZoneEntity extends AuditableEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private double latitude;
    private double longitude;
    private double radius;

    // One zone can have many devices
    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DeviceEntity> devices;

    @ManyToMany(mappedBy = "zones")
    private List<UserEntity> users;  // Zone can be assigned to multiple users// A zone can have multiple devices


}