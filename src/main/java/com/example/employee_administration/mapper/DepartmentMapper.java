package com.example.employee_administration.mapper;

import com.example.employee_administration.dto.DepartmentDTO;
import com.example.employee_administration.model.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper extends EntityMapper<DepartmentDTO, Department>{
}
