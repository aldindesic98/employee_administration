package com.example.employee_administration.service;

import com.example.employee_administration.dto.UserDTO;
import com.example.employee_administration.exception.ServiceException;

import java.util.List;

public interface UserService {

    List<UserDTO> getUsers() throws ServiceException;

    UserDTO getUserById(Long id) throws ServiceException;

    UserDTO saveUser(UserDTO user) throws ServiceException;

    UserDTO updateUser(UserDTO user) throws ServiceException;

    void delete(Long id) throws ServiceException;

}
