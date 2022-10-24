package com.example.employee_administration.service.impl;

import com.example.employee_administration.dto.DepartmentDTO;
import com.example.employee_administration.dto.ExtendedDepartmentDTO;
import com.example.employee_administration.exception.ServiceException;
import com.example.employee_administration.mapper.DepartmentMapper;
import com.example.employee_administration.model.Department;
import com.example.employee_administration.repository.DepartmentRepository;
import com.example.employee_administration.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {

    DepartmentRepository departmentRepository;
    DepartmentMapper departmentMapper;

    DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper){
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    @Override
    public List<DepartmentDTO> getDepartments() throws ServiceException{
        log.info("Getting all departments.");
        try {
            return departmentRepository.findAll().stream().map(departmentMapper::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id) throws ServiceException {
        log.info("Getting a department by ID:" + id);
        try {
            Optional<Department> department = departmentRepository.findById(id);
            if (department.isPresent()) return departmentMapper.toDto(department.get());
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
        throw new ServiceException("Department with ID + " + id + " not found!", HttpStatus.NOT_FOUND);
    }


    @Override
    public List<ExtendedDepartmentDTO> getDepartmentWithAverageSalaryAndNumberOfEmployees() {
        return departmentRepository.getDepartmentWithAverageSalaryAndNumberOfEmployees();
    }

    @Override
    @Transactional
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) throws ServiceException {
        log.info("Creating a new department.");
        try {
            Department department = departmentRepository.save(departmentMapper.toEntity(departmentDTO));
            return departmentMapper.toDto(department);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO) throws ServiceException {
        log.info("Updating a department with ID: " + departmentDTO.getId());
        try {
            Department existingDepartment = departmentRepository.findById(departmentDTO.getId()).orElse(null);
            if (existingDepartment == null)
                throw new ServiceException("Department with ID: " + departmentDTO.getId() + " is not found", HttpStatus.NOT_FOUND);
            existingDepartment = departmentRepository.save(departmentMapper.toEntity(departmentDTO));
            return departmentMapper.toDto(existingDepartment);
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage(), e.getHttpStatusCode());
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }



    @Override
    @Transactional
    public void delete(Long id) throws ServiceException{
        try {
            departmentRepository.deleteById(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
