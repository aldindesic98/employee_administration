package com.example.employee_administration.api;

import com.example.employee_administration.dto.EmployeeDTO;
import com.example.employee_administration.exception.ServiceException;
import com.example.employee_administration.service.EmployeeService;
import com.example.employee_administration.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    @GetMapping
//    public Page<Employee> getEmployees(Pageable pageable){
//        return employeeService.getEmployees(pageable);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(employeeService.getEmployeeById(id));
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveEmployee(employeeDTO));
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeService.updateEmployee(employeeDTO));
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        }
    }

    @DeleteMapping(("/{id}"))
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        try {
            employeeService.delete(id);
            return ResponseEntity.ok().build();
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body(e.getErrorMessage());
        }
    }
}
