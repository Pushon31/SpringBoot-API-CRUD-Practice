package com.sams.Crud.Service;

import com.sams.Crud.Entity.Dept;
import com.sams.Crud.Entity.Student;
import com.sams.Crud.Repository.DeptRepo;
import com.sams.Crud.Repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StudentService {
    private final StudentRepo stdrepo;


    public StudentService(StudentRepo stdrepo){
        this.stdrepo = stdrepo;

    }

    public Student save(Student std){
        return stdrepo.save(std);
    }

    public List<Student> findAll(){
        return stdrepo.findAll();
    }

    public Optional<Student> findbyId(Long id){
        return stdrepo.findById(id);
    }

    public void deleteById(Long id){
        stdrepo.deleteById(id);
    }
}
