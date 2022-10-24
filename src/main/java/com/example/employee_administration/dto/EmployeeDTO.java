package com.example.employee_administration.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private Integer salary;

    private DepartmentDTO departmentDTO;

}
