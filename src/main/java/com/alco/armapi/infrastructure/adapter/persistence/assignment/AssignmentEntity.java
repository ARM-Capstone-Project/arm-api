package com.alco.armapi.infrastructure.adapter.persistence.assignment;

import com.alco.armapi.common.AuditableEntity;
import com.alco.armapi.infrastructure.adapter.persistence.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
@Data
@Entity
@Table(name = "assignments")
public class AssignmentEntity extends AuditableEntity implements Serializable {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private String id;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private UserEntity manager;

    @ManyToOne
    @JoinColumn(name = "operator_id")
    private UserEntity operator;

    public AssignmentEntity() {}

    public AssignmentEntity(UserEntity manager, UserEntity operator) {
        this.manager = manager;
        this.operator = operator;
    }
}
