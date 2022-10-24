package com.example.employee_administration.mapper;

import com.example.employee_administration.dto.EmployeeDTO;
import com.example.employee_administration.model.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends EntityMapper<EmployeeDTO, Employee>{
}
