package com.sams.Crud.DTO;

import lombok.Data;

import java.util.List;

@Data
public class DeptDTO {
    private Long id;
    private String name;


    private List<StudentDTO> students;
}
