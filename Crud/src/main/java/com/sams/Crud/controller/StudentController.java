package com.sams.Crud.controller;

import com.sams.Crud.DTO.StudentDTO;
import com.sams.Crud.Entity.Student;
import com.sams.Crud.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service){
        this.service = service;

    }

    @PostMapping
    public StudentDTO create(@Valid @RequestBody StudentDTO studentDTO){
        return service.save(studentDTO);
    }

    @GetMapping
    public List<StudentDTO> getAll(){
        return service.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentDTO getById(@PathVariable Long id){

        return service.getStudentById(id);
    }

    @PutMapping("/{id}")
    public StudentDTO update(@PathVariable Long id, @Valid @RequestBody StudentDTO studentDTO){
        studentDTO.setId(id);
        return service.save(studentDTO);

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteById(id);
    }

//    @GetMapping("/dto/{id}")
//    public StudentDTO getStudentById(@PathVariable Long id){
//        return service.getStudentById(id);
//    }
}
