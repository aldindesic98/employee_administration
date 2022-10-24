package com.example.employee_administration.service.impl;

import com.example.employee_administration.dto.AuthorityDTO;
import com.example.employee_administration.exception.ServiceException;
import com.example.employee_administration.mapper.AuthorityMapper;
import com.example.employee_administration.model.Authority;
import com.example.employee_administration.repository.AuthorityRepository;
import com.example.employee_administration.service.AuthorityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
public class AuthorityServiceImpl implements AuthorityService {

    AuthorityRepository roleRepository;
    AuthorityMapper roleMapper;

    AuthorityServiceImpl(AuthorityRepository roleRepository, AuthorityMapper roleMapper) throws ServiceException {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<AuthorityDTO> getAuthorities() throws ServiceException {
        log.info("Getting all roles.");
        try {
            return roleRepository.findAll().stream().map(roleMapper::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public AuthorityDTO getAuthorityById(Long id) throws ServiceException {
        log.info("Getting a role by ID:" + id);
        try {
            Optional<Authority> role = roleRepository.findById(id);
            if (role.isPresent()) return roleMapper.toDto(role.get());
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
        throw new ServiceException("Authority with ID + " + id + " not found!", HttpStatus.NOT_FOUND);
    }

    @Override
    @Transactional
    public AuthorityDTO saveAuthority(AuthorityDTO authorityDTO) throws ServiceException {
        log.info("Creating a new role.");
        try {
            Authority role = roleRepository.save(roleMapper.toEntity(authorityDTO));
            return roleMapper.toDto(role);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public AuthorityDTO updateAuthority(AuthorityDTO roleDTO) throws ServiceException {
        log.info("Updating a role with ID: " + roleDTO.getId());
        try {
            Authority existingAuthority = roleRepository.findById(roleDTO.getId()).orElse(null);
            if (existingAuthority == null)
                throw new ServiceException("Authority with ID: " + roleDTO.getId() + " is not found", HttpStatus.NOT_FOUND);
            existingAuthority = roleRepository.save(roleMapper.toEntity(roleDTO));
            return roleMapper.toDto(existingAuthority);
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
            roleRepository.deleteById(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
