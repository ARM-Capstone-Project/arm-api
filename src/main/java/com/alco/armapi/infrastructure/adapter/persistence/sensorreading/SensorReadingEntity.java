package com.alco.armapi.infrastructure.adapter.persistence.sensorreading;

import com.alco.armapi.common.AuditableEntity;
import com.alco.armapi.infrastructure.adapter.persistence.sensor.SensorEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
//import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import com.alco.armapi.infrastructure.adapter.persistence.device.DeviceEntity;

@Getter
@Setter
@Entity
@Table(name = "sensor_readings")
public class SensorReadingEntity extends AuditableEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  // Automatically generated UUID. Updated from GenericGenerator since deprecated.
    private UUID id;   // using UUID directly is more efficient and avoids conversion.
    //id for internal usage

    private LocalDateTime timestamp;
    private double value;
    private String unit;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private SensorEntity sensor;  // A reading belongs to one sensor

    @ManyToOne
    @JoinColumn(name ="device_id")
    private DeviceEntity device; //to get device from components (sensors)
}