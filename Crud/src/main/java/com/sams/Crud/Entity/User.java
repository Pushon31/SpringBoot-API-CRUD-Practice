package com.sams.Crud.Entity;

import com.sams.Crud.config.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity

@Table(name="users")
@Data
public class User extends BaseEntity {
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String password;

    @OneToOne(mappedBy = "user",
               cascade = CascadeType.ALL,
               orphanRemoval = true,
               fetch = FetchType.LAZY)
    private Profile profile;

    //helper to link both sides
    public void setProfile(Profile profile){
        this.profile = profile;
        if (profile !=null) profile.setUser(this);
    }
}
