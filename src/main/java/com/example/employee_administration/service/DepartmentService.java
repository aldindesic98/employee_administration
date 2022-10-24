package com.example.employee_administration.service;

import com.example.employee_administration.dto.DepartmentDTO;
import com.example.employee_administration.dto.ExtendedDepartmentDTO;
import com.example.employee_administration.exception.ServiceException;
import com.example.employee_administration.model.Department;

import java.util.List;



public interface DepartmentService {

    List<DepartmentDTO> getDepartments() throws ServiceException;

    DepartmentDTO getDepartmentById(Long id) throws ServiceException;

    List<ExtendedDepartmentDTO> getDepartmentWithAverageSalaryAndNumberOfEmployees();

    DepartmentDTO saveDepartment(DepartmentDTO department) throws ServiceException;

    DepartmentDTO updateDepartment(DepartmentDTO department) throws ServiceException;

    void delete(Long id) throws ServiceException;
}
