package com.sams.Crud.Service;

import com.sams.Crud.DTO.CourseDTO;
import com.sams.Crud.Entity.Courses;
import com.sams.Crud.Entity.Student;
import com.sams.Crud.Repository.CoursesRepo;
import com.sams.Crud.Repository.StudentRepo;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Data
@Service
public class CoursesService {
    private final  CoursesRepo coursesRepo;
    private final StudentRepo studentRepo;

    public CoursesService(CoursesRepo coursesRepo, StudentRepo studentRepo) {
        this.coursesRepo = coursesRepo;
        this.studentRepo = studentRepo;
    }


    //Create Course
    public CourseDTO createCourse(CourseDTO courseDTO)
    {
        Courses course = new Courses();
        course.setName(courseDTO.getName());

        if (courseDTO.getStudentIds() != null) {
            course.setStudents(new HashSet<>(studentRepo.findAllById(courseDTO.getStudentIds())));
        }
        Courses saved =  coursesRepo.save(course);
        return mapToDTO(saved);
    }

    //Get All Courses
    public List<CourseDTO> getAllCourses(){
        List<Courses> coursesList = coursesRepo.findAll();
        return coursesList.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    //Get Course by ID
    public CourseDTO getCourseById(Long id)
    {
        Optional<Courses> course = coursesRepo.findById(id);
        return  course.map(this::mapToDTO).orElse(null);
    }

    //Update course
    public CourseDTO updateCourse(Long id, CourseDTO courseDTO)
    {
        Courses course = coursesRepo.findById(id).orElseThrow(()-> new RuntimeException("course not found"));
        course.setName(courseDTO.getName());

        if (courseDTO.getStudentIds() != null) {
            course.setStudents(new HashSet<>(studentRepo.findAllById(courseDTO.getStudentIds())));
        }
        return mapToDTO(coursesRepo.save(course));
    }

    //Delete

    public void deleteCourse(Long id)
    {coursesRepo.deleteById(id);
    }

    //Mapper : Entity -> DTO
    private CourseDTO mapToDTO(Courses course)
    {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setName(course.getName());

        if (course.getStudents() != null) {
            dto.setStudentIds(course.getStudents()
                    .stream()
                    .map(Student::getId)
                    .collect(Collectors.toSet()));
        }

        return dto;
    }

}
