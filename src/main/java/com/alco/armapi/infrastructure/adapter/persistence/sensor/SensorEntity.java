package com.alco.armapi.infrastructure.adapter.persistence.sensor;

import com.alco.armapi.common.AuditableEntity;
import com.alco.armapi.infrastructure.adapter.persistence.device.DeviceEntity;
import com.alco.armapi.infrastructure.adapter.persistence.sensorreading.SensorReadingEntity;
import jakarta.persistence.*;
import lombok.Data;
//import org.hibernate.annotations.GenericGenerator;

import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)  // Include superclass, AuditableEntity fields
@Entity
@Table(name = "sensors")
public class SensorEntity extends AuditableEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // Automatically generated UUID
    private UUID id;

    private String name;
    private String type;
    private String status;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private DeviceEntity device;  // A sensor belongs to one device

    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SensorReadingEntity> sensorReadings;  // A sensor can have multiple readings
}
