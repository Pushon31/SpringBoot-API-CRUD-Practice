package com.sams.Crud.Service;

import com.sams.Crud.DTO.DeptDTO;
import com.sams.Crud.DTO.StudentDTO;
import com.sams.Crud.Entity.Courses;
import com.sams.Crud.Entity.Dept;
import com.sams.Crud.Entity.Student;
import com.sams.Crud.Repository.CoursesRepo;
import com.sams.Crud.Repository.DeptRepo;
import com.sams.Crud.Repository.StudentRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service


public class StudentService {
    private final StudentRepo stdrepo;
    private final CoursesRepo coursesrepo;
    private final DeptRepo deptrepo;


    public StudentService(StudentRepo stdrepo , CoursesRepo coursesrepo , DeptRepo deptrepo) {
        this.stdrepo = stdrepo;
        this.coursesrepo = coursesrepo;
        this.deptrepo = deptrepo;

    }
    @Transactional
    public StudentDTO save(StudentDTO studentDTO){
        Student student = toEntity(studentDTO);
        return toDTO(stdrepo.save(student)) ;
    }

    public List<StudentDTO> getAllStudents(){
        return
                stdrepo.findAll().stream().map(this::toDTO).toList();
    }

    public StudentDTO getStudentById(Long id){
        return stdrepo.findById(id).map(this::toDTO).orElseThrow(()-> new RuntimeException("Student not found"));
    }

    public void deleteById(Long id){
        if(!stdrepo.existsById(id)){
            throw new RuntimeException("Student not found");
        }
        stdrepo.deleteById(id);
    }

//    public StudentDTO getStudentDTOById(Long id){
//        Optional<Student> st = stdrepo.findById(id);
//        StudentDTO std = new StudentDTO();
//
//        st.ifPresent(student -> {
//            BeanUtils.copyProperties(student, std);
//            Set<Long> oldC =student.getCourses().stream().map(Courses::getId).collect(Collectors.toSet());
//        });
//        return std;


//        if (st.isPresent()){
//            BeanUtils.copyProperties(st.get(),std);
//
//        }
//        return std;
//    }

    public  StudentDTO toDTO(Student student){
        if(student == null)return null;
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setDob(student.getDob());

        if(student.getDepartment() != null){
            DeptDTO dp = new DeptDTO();
            dp.setId(student.getDepartment().getId());
            dp.setName(student.getDepartment().getDeptName());
            dto.setDeptDTO(dp);
        }

//        Set<Courses> courses = coursesrepo.findCoursesByStudentId(student.getId());
        if (student.getCourses() != null){
            dto.setCourseIds(
                    student.getCourses().stream().map(Courses::getId).collect(Collectors.toSet())
            );
        }return dto;
    }


    public Student toEntity(StudentDTO dto){
        if(dto == null)return null;

        Student student = new Student();
        if (dto.getId() != null) {
            student.setId(dto.getId());
        }

        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setDob(dto.getDob());
        if(dto.getDeptDTO() != null){
            Dept dt =  deptrepo.findById(dto.getDeptDTO().getId()).orElseThrow(()-> new RuntimeException("DeptDTO not found"));
            student.setDepartment(dt);
        }
        if (dto.getCourseIds() != null && !dto.getCourseIds().isEmpty()) {
            Set<Courses> courses = new HashSet<Courses>(coursesrepo.findAllById(dto.getCourseIds()));
            student.getCourses().clear();
            student.getCourses().addAll(courses);
        }
        return student;
    }


}
