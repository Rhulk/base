package com.alquiler.reservas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alquiler.reservas.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	
	public List<User>  findByUsername(String username);
	
	
	public List<User> findByIdAndPassword(Long id, String password);

}