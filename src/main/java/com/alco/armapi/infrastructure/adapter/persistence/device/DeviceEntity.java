package com.alco.armapi.infrastructure.adapter.persistence.device;
import com.alco.armapi.common.AuditableEntity;
import com.alco.armapi.infrastructure.adapter.persistence.sensor.SensorEntity;
import com.alco.armapi.infrastructure.adapter.persistence.zone.ZoneEntity;
import jakarta.persistence.*;
import java.util.UUID;

import java.io.Serializable;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Data;

@Data
@EqualsAndHashCode(callSuper = true) 
@Entity
@Table(name = "devices")
public class DeviceEntity extends AuditableEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // Automatically generated UUID
    private UUID id;   // using UUID directly is more efficient and avoids conversion.
    //id for internal usage

    private String name;
    private String batchNo;
    private String description;

    // Many devices belong to one zone, and referencing the zone's id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id", referencedColumnName = "id", nullable = false)  // reference to ZoneEntity's 'id' field
    private ZoneEntity zone;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SensorEntity> sensors;  // A device can have multiple sensors

    public void setZone(ZoneEntity zone) {
        this.zone=zone;
    }
}