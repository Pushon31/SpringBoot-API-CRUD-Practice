package com.sams.Crud.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
@Data
public class StudentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dob;
    private String department;
    private Set<Long> courseIds;

    private DeptDTO deptDTO;
}
