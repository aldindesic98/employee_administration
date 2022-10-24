package com.example.employee_administration.service.impl;

import com.example.employee_administration.dto.EmployeeDTO;
import com.example.employee_administration.exception.ServiceException;
import com.example.employee_administration.mapper.EmployeeMapper;
import com.example.employee_administration.model.Employee;
import com.example.employee_administration.repository.EmployeeRepository;
import com.example.employee_administration.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }


    @Override
    public Page<Employee> getEmployees(Pageable pageable) {
        return null;
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) throws ServiceException{
        log.info("Getting an employee by ID:" + id);
        try {
            Optional<Employee> employee = employeeRepository.findById(id);
            if (employee.isPresent()) return employeeMapper.toDto(employee.get());
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
        throw new ServiceException("Employee with ID + " + id + " not found!", HttpStatus.NOT_FOUND);
    }

    @Override
    @Transactional
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) throws ServiceException{
        log.info("Creating a new employee.");
        try {
            Employee employee = employeeRepository.save(employeeMapper.toEntity(employeeDTO));
            return employeeMapper.toDto(employee);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) throws ServiceException{
        log.info("Updating an employee with ID: " + employeeDTO.getId());
        try {
            Employee existingEmployee = employeeRepository.findById(employeeDTO.getId()).orElse(null);
            if (existingEmployee == null)
                throw new ServiceException("Employee with ID: " + employeeDTO.getId() + " is not found", HttpStatus.NOT_FOUND);
            existingEmployee = employeeRepository.save(employeeMapper.toEntity(employeeDTO));
            return employeeMapper.toDto(existingEmployee);
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
            employeeRepository.deleteById(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
