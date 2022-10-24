package com.example.employee_administration.service.impl;

import com.example.employee_administration.dto.UserDTO;
import com.example.employee_administration.exception.ServiceException;
import com.example.employee_administration.mapper.UserMapper;
import com.example.employee_administration.model.User;
import com.example.employee_administration.repository.UserRepository;
import com.example.employee_administration.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserMapper userMapper;

    UserServiceImpl(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public List<UserDTO> getUsers() throws ServiceException {
        log.info("Getting all users.");
        try {
            return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public UserDTO getUserById(Long id) throws ServiceException {
        log.info("Getting a user by ID:" + id);
        try {
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) return userMapper.toDto(user.get());
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
        throw new ServiceException("User with ID + " + id + " not found!", HttpStatus.NOT_FOUND);    }

    @Override
    @Transactional
    public UserDTO saveUser(UserDTO userDTO) throws ServiceException {
        log.info("Creating a new user.");
        try {
            User user = userRepository.save(userMapper.toEntity(userDTO));
            return userMapper.toDto(user);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public UserDTO updateUser(UserDTO userDTO) throws ServiceException {
        log.info("Updating a user with ID: " + userDTO.getId());
        try {
            User existingUser = userRepository.findById(userDTO.getId()).orElse(null);
            if (existingUser == null)
                throw new ServiceException("User with ID: " + userDTO.getId() + " is not found", HttpStatus.NOT_FOUND);
            existingUser = userRepository.save(userMapper.toEntity(userDTO));
            return userMapper.toDto(existingUser);
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage(), e.getHttpStatusCode());
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(Long id) throws ServiceException {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
