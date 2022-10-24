package com.example.employee_administration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtendedDepartmentDTO {
    private Long id;
    private String name;
    private Long countOfEmployees;
    private Double avgSalary;
}
