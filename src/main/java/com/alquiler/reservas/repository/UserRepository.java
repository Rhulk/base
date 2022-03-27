package com.alquiler.reservas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alquiler.reservas.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	public List<User>  findByUsername(String username);
		
	public List<User> findByIdAndPassword(Long id, String password);
	
	@Query(value = "SELECT table_name FROM information_schema.tables ", nativeQuery=true)
	public List<String> getTablas();

	//public List<String> getCampos(String table);




}