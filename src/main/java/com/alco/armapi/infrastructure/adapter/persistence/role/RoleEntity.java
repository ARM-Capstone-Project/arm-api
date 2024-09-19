package com.alco.armapi.infrastructure.adapter.persistence.role;

import com.alco.armapi.common.Auditable;
import com.alco.armapi.common.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;

@AllArgsConstructor
@Entity
@Data
@Table(name = "roles")
public class RoleEntity extends AuditableEntity implements Serializable {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private String id;
    private String name;
    private String description;

    public RoleEntity() {

    }
}
