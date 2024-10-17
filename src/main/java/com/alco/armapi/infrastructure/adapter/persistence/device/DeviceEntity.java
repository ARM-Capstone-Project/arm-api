package com.alco.armapi.infrastructure.adapter.persistence.device;
import com.alco.armapi.common.AuditableEntity;
import com.alco.armapi.infrastructure.adapter.persistence.sensor.SensorEntity;
import com.alco.armapi.infrastructure.adapter.persistence.zone.ZoneEntity;
import com.alco.armapi.infrastructure.adapter.persistence.user.UserEntity;
import jakarta.persistence.*;
import java.util.UUID;
import java.io.Serializable;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@EqualsAndHashCode(callSuper = true) 
@Entity
@Table(name = "devices")
@AllArgsConstructor
@NoArgsConstructor
public class DeviceEntity extends AuditableEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // Automatically generated UUID
    private UUID id;   // using UUID directly is more efficient and avoids conversion.
    //id for internal usage

    private String name;
    private String batchNo;
    private String description;
    private String type; //example: Gas Detector
    private String location; //Building or Street in description e.g. Ang Mo Kio Ave 1
    private String tagNo; //user defined device ID

    // Many devices belong to one zone, and referencing the zone's id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "zone_id", referencedColumnName = "id", nullable = true)  // reference to ZoneEntity's 'id' field
    private ZoneEntity zone;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SensorEntity> sensors;  // A device can have multiple sensors

    // public void setZone(ZoneEntity zone) {
    //     this.zone=zone;
    // }

    @Column(nullable = false)
    private String status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "devices_users",
            joinColumns = @JoinColumn(name = "device_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private List<UserEntity> users;

    // Method called before persisting a new DeviceEntity to set default values
    @PrePersist
    public void prePersist() {
        if (this.status == null) {
            this.status = "active";  // Default status
        }
    }
}