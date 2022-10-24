package com.example.employee_administration.mapper;

import com.example.employee_administration.dto.UserDTO;
import com.example.employee_administration.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User>{
}
