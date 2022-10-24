package com.example.employee_administration.service;

import com.example.employee_administration.dto.EmployeeDTO;
import com.example.employee_administration.exception.ServiceException;
import com.example.employee_administration.model.Employee;
import com.example.employee_administration.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

public interface EmployeeService {

    Page<Employee> getEmployees(Pageable pageable) throws ServiceException;

    EmployeeDTO getEmployeeById(Long id) throws ServiceException;

    EmployeeDTO saveEmployee(EmployeeDTO employee) throws ServiceException;

    EmployeeDTO updateEmployee(EmployeeDTO employee) throws ServiceException;

    void delete(Long id) throws ServiceException;


}
