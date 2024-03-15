package com.alquiler.reservas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alquiler.reservas.entity.Curso;
import com.alquiler.reservas.entity.CursoUser;
import com.alquiler.reservas.entity.User;

@Repository
public interface CursoUserRepository extends CrudRepository<CursoUser, Long>{
	
	public CursoUser findByCursoAndUser(Curso curso, User user);

}
