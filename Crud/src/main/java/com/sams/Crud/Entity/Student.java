package com.sams.Crud.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sams.Crud.config.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Student extends BaseEntity {
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be 2-50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be 2-50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;  // Date of Birth

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonIgnoreProperties("students")
    private Dept department;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
          name = "student_course",
          joinColumns = @JoinColumn (name = "student_id"),
          inverseJoinColumns = @JoinColumn(name = "course_id")

    )
    private Set<Courses> courses = new HashSet<>();


}
