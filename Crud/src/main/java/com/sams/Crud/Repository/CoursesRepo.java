package com.sams.Crud.Repository;

import com.sams.Crud.Entity.Courses;
import com.sams.Crud.Entity.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CoursesRepo extends JpaRepository<Courses,Long> {
//    @Query("SELECT c FROM Student s JOIN s.courses c WHERE s.id = :studentId")
//    Set<Courses> findCoursesByStudentId(Long studentId);
}
