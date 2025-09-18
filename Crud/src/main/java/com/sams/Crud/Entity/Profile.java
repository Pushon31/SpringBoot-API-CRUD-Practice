package com.sams.Crud.Entity;

import com.sams.Crud.config.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "profiles")
public class Profile extends BaseEntity {
    private String firstName;
    private String lastName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
