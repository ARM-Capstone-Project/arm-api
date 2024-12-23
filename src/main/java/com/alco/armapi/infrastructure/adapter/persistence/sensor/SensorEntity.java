package com.alco.armapi.infrastructure.adapter.persistence.sensor;

import com.alco.armapi.common.AuditableEntity;
import com.alco.armapi.infrastructure.adapter.persistence.device.DeviceEntity;
import com.alco.armapi.infrastructure.adapter.persistence.sensorreading.SensorReadingEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sensors")
@AllArgsConstructor
@NoArgsConstructor
public class SensorEntity extends AuditableEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = true)
    private String name;
    private String type;
    private String status;
    private String unit;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = true)
    private DeviceEntity device;  // A sensor belongs to one device

    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SensorReadingEntity> sensorReadings = new ArrayList<>();  // A sensor can have multiple readings

    @PrePersist
    public void prePersist() {
        if (this.status == null) {
            this.status = "active";  // Default status
        }
    }
}