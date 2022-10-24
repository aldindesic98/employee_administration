package com.example.employee_administration.mapper;

import java.util.List;

public interface EntityMapper<D, E>{

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityCollection);
}
