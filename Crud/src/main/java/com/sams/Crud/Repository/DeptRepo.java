package com.sams.Crud.Repository;


import com.sams.Crud.Entity.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptRepo extends JpaRepository<Dept,Long>{
}
