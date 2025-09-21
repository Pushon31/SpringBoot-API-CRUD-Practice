package com.sams.Crud.Repository;


import com.sams.Crud.Entity.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeptRepo extends JpaRepository<Dept,Long>{
    Optional<Dept> findByDeptName(String deptName);
}
