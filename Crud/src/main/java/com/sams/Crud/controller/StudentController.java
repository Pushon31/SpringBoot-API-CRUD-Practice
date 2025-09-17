package com.sams.Crud.controller;

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
    public Student create(@Valid @RequestBody Student dept){
        return service.save(dept);
    }

    @GetMapping
    public List<Student> getAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id, @Valid @RequestBody Student dept){
        dept.setId(id);
        return service.findbyId(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @Valid @RequestBody Student dept){
        dept.setId(id);
        return service.save(dept);

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteById(id);
    }
}
