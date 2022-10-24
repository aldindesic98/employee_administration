package com.example.employee_administration.service;

import com.example.employee_administration.dto.AuthorityDTO;
import com.example.employee_administration.exception.ServiceException;

import java.util.List;

public interface AuthorityService {

    List<AuthorityDTO> getAuthorities() throws ServiceException;

    AuthorityDTO getAuthorityById(Long id) throws ServiceException;

    AuthorityDTO saveAuthority(AuthorityDTO authority) throws ServiceException;

    AuthorityDTO updateAuthority(AuthorityDTO authority) throws ServiceException;

    void delete(Long id) throws ServiceException;
}
