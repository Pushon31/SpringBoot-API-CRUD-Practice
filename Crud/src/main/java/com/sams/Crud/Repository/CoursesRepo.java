package com.sams.Crud.Repository;

import com.sams.Crud.Entity.Courses;
import com.sams.Crud.Entity.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepo extends JpaRepository<Courses,Long> {
}
