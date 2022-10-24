package com.example.employee_administration.api;

import com.example.employee_administration.dto.DepartmentDTO;
import com.example.employee_administration.exception.ServiceException;
import com.example.employee_administration.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/department")
@CrossOrigin(origins = "*")
public class DepartmentController {

    private final DepartmentService departmentService;


    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/")
    public  ResponseEntity<?> getDepartments(){
        try {
            return ResponseEntity.ok(departmentService.getDepartments());
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        }
    }

    @GetMapping(name = "/list")
    public ResponseEntity<?> getDepartmentWithAverageSalaryAndNumberOfEmployees(){
        return ResponseEntity.ok(departmentService.getDepartmentWithAverageSalaryAndNumberOfEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(departmentService.getDepartmentById(id));
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<?> saveDepartment(@RequestBody DepartmentDTO departmentDTO){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.saveDepartment(departmentDTO));
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateDepartment(@RequestBody DepartmentDTO departmentDTO) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(departmentService.updateDepartment(departmentDTO));
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        try {
            departmentService.delete(id);
            return ResponseEntity.ok().build();
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        }
    }
}
