package com.alco.armapi.infrastructure.adapter.persistence.sensor;

import com.alco.armapi.common.AuditableEntity;
import com.alco.armapi.infrastructure.adapter.persistence.device.DeviceEntity;
import com.alco.armapi.infrastructure.adapter.persistence.sensorreading.SensorReadingEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "sensors")
public class SensorEntity extends AuditableEntity implements Serializable {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private String id;

    private String name;
    private String type;
    private String status;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private DeviceEntity device;  // A sensor belongs to one device

    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SensorReadingEntity> sensorReadings;  // A sensor can have multiple readings
}
