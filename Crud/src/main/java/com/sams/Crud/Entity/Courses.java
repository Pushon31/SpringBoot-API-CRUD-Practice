package com.sams.Crud.Entity;

import com.sams.Crud.config.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Courses extends BaseEntity {
    private String name;
    @ManyToMany(mappedBy = "courses")
//    @JsonIgnore
    private Set<Student> students = new HashSet<>();
}
