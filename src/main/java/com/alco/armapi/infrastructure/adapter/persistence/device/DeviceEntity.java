package com.alco.armapi.infrastructure.adapter.persistence.device;
import com.alco.armapi.common.AuditableEntity;
import com.alco.armapi.infrastructure.adapter.persistence.sensor.SensorEntity;
import com.alco.armapi.infrastructure.adapter.persistence.zone.ZoneEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "devices")
public class DeviceEntity extends AuditableEntity implements Serializable {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private String id;

    private String name;
    private String batchNo;
    private String description;

//    @Setter
//    @ManyToOne
//    @JoinColumn(name = "zone_id")
//    private ZoneEntity zone;  // A device belongs to one zone
    @ManyToMany(mappedBy = "devices")
    private Set<ZoneEntity> zones;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SensorEntity> sensors;  // A device can have multiple sensors

    public void setZone(ZoneEntity zone) {
        this.zones.add(zone);
    }
}