package com.example.employee_administration.mapper;

import com.example.employee_administration.dto.AuthorityDTO;
import com.example.employee_administration.model.Authority;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorityMapper extends EntityMapper<AuthorityDTO, Authority>{
}
