package com.alco.armapi.infrastructure.adapter.persistence.sensorreading;

import com.alco.armapi.common.AuditableEntity;
import com.alco.armapi.infrastructure.adapter.persistence.sensor.SensorEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "sensor_readings")
public class SensorReadingEntity extends AuditableEntity implements Serializable {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private String id;

    private LocalDateTime timestamp;
    private double value;
    private String unit;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private SensorEntity sensor;  // A reading belongs to one sensor
}