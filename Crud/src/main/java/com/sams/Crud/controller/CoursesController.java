package com.sams.Crud.controller;

import com.sams.Crud.DTO.CourseDTO;
import com.sams.Crud.DTO.StudentDTO;
import com.sams.Crud.Service.CoursesService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/courses")
public class CoursesController {
    private final CoursesService coursesService;


    public CoursesController(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    @PostMapping
    public CourseDTO  createCourse(@RequestBody CourseDTO courseDTO) {
      return   coursesService.createCourse(courseDTO);
    }

    @GetMapping
    public List<CourseDTO> getCourses() {
        return coursesService.getAllCourses();
    }


    @GetMapping("/{id}")
    public CourseDTO getCourseById(@PathVariable Long id){

        return coursesService.getCourseById(id);
    }

    @PutMapping("/{id}")
    public CourseDTO update(@PathVariable Long id, @Valid @RequestBody CourseDTO courseDTO){

        return coursesService.updateCourse(id,courseDTO);

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        coursesService.deleteCourse(id);
    }

}
