package com.example.employee_administration.repository;

import com.example.employee_administration.dto.DepartmentDTO;
import com.example.employee_administration.dto.ExtendedDepartmentDTO;
import com.example.employee_administration.model.Department;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long>
{
    @Query(value = "SELECT NEW com.example.employee_administration.dto.ExtendedDepartmentDTO(d.id, d.name, COUNT(e.id), AVG(e.salary)) " +
            "FROM Department d " +
            "INNER JOIN Employee e on e.department = d " +
            "GROUP BY d.id, d.name")
    List<ExtendedDepartmentDTO> getDepartmentWithAverageSalaryAndNumberOfEmployees();

    //Optional<DepartmentDTO> findById(Long id);
}
