package com.alquiler.reservas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alquiler.reservas.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{

}