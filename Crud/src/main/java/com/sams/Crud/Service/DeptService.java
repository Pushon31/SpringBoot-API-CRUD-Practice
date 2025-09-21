package com.sams.Crud.Service;

import com.sams.Crud.DTO.DeptDTO;
import com.sams.Crud.DTO.StudentDTO;
import com.sams.Crud.Entity.Dept;
import com.sams.Crud.Repository.DeptRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeptService {
    private final DeptRepo repository;

    public DeptService(DeptRepo repository){
        this.repository = repository;

    }
//    @Transactional
//    public ResponseEntity<?> saveDepartment(DeptDTO deptDTO){
//       try {
//           Optional<Dept> existing =repository.findByDeptName(deptDTO.getName());
//           if (existing.isPresent()){
//               return ResponseEntity.status(HttpStatus.CONFLICT).body("Department already exist with name : "+deptDTO.getName());
//           }
//           Dept saved = repository.save(toEntity(deptDTO));
//           return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(saved));
//       }catch (Exception e){
//           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create department"+e.getMessage());
//       }
//    }

    public List<Dept> findAll(){
        return repository.findAll();
    }

    public Optional<Dept> findbyId(Long id){
        return repository.findById(id);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public DeptDTO toDTO(Dept dept){
        if (dept == null)
            return null;
        DeptDTO deptDTO = new DeptDTO();
        deptDTO.setId(dept.getId());
        deptDTO.setName(dept.getDeptName());

        //Map Students to STudent to Dto
        if (dept.getStudents()!=null){
            List<StudentDTO> students = dept.getStudents().stream().map(s->{
                StudentDTO studentDTO = new StudentDTO();
                studentDTO.setId(s.getId());
                studentDTO.setFirstName(s.getFirstName());
                studentDTO.setLastName(s.getLastName());
                studentDTO.setEmail(s.getEmail());
                return studentDTO;
            }).collect(Collectors.toList());
            deptDTO.setStudents(students);
        }return  deptDTO;

    }

    public Dept toEntity(DeptDTO dto) {
        if (dto == null) return null;
        Dept dept = new Dept();
        dept.setId(dto.getId());
        dept.setDeptName(dto.getName());
        // Note: We do not set students here to avoid overriding existing ones
        return dept;
    }
}
