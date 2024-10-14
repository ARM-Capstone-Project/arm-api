package com.alco.armapi.infrastructure.adapter.persistence.sensorreading;

import com.alco.armapi.common.AuditableEntity;
import com.alco.armapi.infrastructure.adapter.persistence.sensor.SensorEntity;
import jakarta.persistence.*;
import java.util.UUID;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)  // Include superclass, AuditableEntity fields
@Entity
@Table(name = "sensor_readings")
@AllArgsConstructor
@NoArgsConstructor
public class SensorReadingEntity extends AuditableEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDateTime timestamp;
    private double value;
    private String unit;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private SensorEntity sensor;  // A reading belongs to one sensor
}