package com.alco.armapi.infrastructure.adapter.persistence.device;

import com.alco.armapi.common.AuditableEntity;
import com.alco.armapi.infrastructure.adapter.persistence.sensor.SensorEntity;
import com.alco.armapi.infrastructure.adapter.persistence.zone.ZoneEntity;
import com.alco.armapi.infrastructure.adapter.persistence.user.UserEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Data;
import java.util.List;
import java.util.UUID;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)  // Include superclass, AuditableEntity fields
@Entity
@Table(name = "devices")
public class DeviceEntity extends AuditableEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // Automatically generated UUID
    private UUID id;   // using UUID directly is more efficient and avoids conversion.
    //id for internal usage

    @Column(nullable = false)
    private String name;

    private String batchNo;//identifying number during batch creation of devices
    private String description; //device name
    private String type; //example: Gas Detector
    private String location; //Building or Street in description e.g. Ang Mo Kio Ave 1
    private double latitude;
    private double longitude;

    @Column(nullable = false)
    private String status; //active | inactive

    // Many devices belong to one zone, and referencing the zone's id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id", referencedColumnName = "id", nullable = false)  // reference to ZoneEntity's 'id' field
    private ZoneEntity zone;

    // A device can have multiple sensors
    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SensorEntity> sensors;

    //A device can have assigned users responsible
    @OneToMany(mappedBy ="device", cascade =CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserEntity> users;

    // Method called before persisting a new DeviceEntity to set default values
    @PrePersist
    public void prePersist() {
        if (this.status == null) {
            this.status = "active";  // Default status
        }
    }
}