package com.sams.Crud.controller;

import com.sams.Crud.Entity.Dept;
import com.sams.Crud.Service.DeptService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DeptController {

    private final DeptService service;

    public DeptController(DeptService service){
        this.service = service;

    }

    @PostMapping
    public Dept create(@Valid @RequestBody Dept dept){
        return service.save(dept);
    }

    @GetMapping
    public List<Dept> getAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Dept getById(@PathVariable Long id, @Valid @RequestBody Dept dept){
        dept.setId(id);
        return service.findbyId(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Dept update(@PathVariable Long id, @Valid @RequestBody Dept dept){
        dept.setId(id);
        return service.save(dept);

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteById(id);
    }


}
