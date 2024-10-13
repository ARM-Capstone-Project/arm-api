package com.alco.armapi.infrastructure.adapter.persistence.zone;

import com.alco.armapi.common.AuditableEntity;
import com.alco.armapi.infrastructure.adapter.persistence.device.DeviceEntity;
import com.alco.armapi.infrastructure.adapter.persistence.user.UserEntity;
import jakarta.persistence.*;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Set;

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

//    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Set<DeviceEntity> devices;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "zones_devices",
            joinColumns = @JoinColumn(name = "zone_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "device_id", referencedColumnName = "id")
    )
    private Set<DeviceEntity> devices;

    @ManyToMany(mappedBy = "zones")
    private Set<UserEntity> users;  // Zone can be assigned to multiple users// A zone can have multiple devices


}