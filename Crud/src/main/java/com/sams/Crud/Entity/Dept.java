package com.sams.Crud.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sams.Crud.config.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true,onlyExplicitlyIncluded = true)
@Table (
        name = "Department",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_DEPARTMENT_NAME",columnNames = {"name"})
        }
)

public class Dept extends BaseEntity {

    @NotBlank(message = "Department name is required")
    @Size(min=2 ,max = 100)
    private String deptName;

    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    @JsonIgnoreProperties("department")
    private List<Student> students;

}
