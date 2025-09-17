package com.sams.Crud.Service;

import com.sams.Crud.Entity.Dept;
import com.sams.Crud.Repository.DeptRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeptService {
    private final DeptRepo repository;

    public DeptService(DeptRepo repository){
        this.repository = repository;

    }

    public Dept save(Dept dept){
        return repository.save(dept);
    }

    public List<Dept> findAll(){
        return repository.findAll();
    }

    public Optional<Dept> findbyId(Long id){
        return repository.findById(id);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
