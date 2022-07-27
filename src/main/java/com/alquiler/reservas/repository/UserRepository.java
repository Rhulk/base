package com.alquiler.reservas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alquiler.reservas.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	public List<User>  findByUsername(String username);
		
	public List<User> findByIdAndPassword(Long id, String password);
	
	@Query(value = "SELECT table_name FROM information_schema.tables ", nativeQuery=true)
	public List<String> getTablas();
	
	@Query(value = "SELECT * FROM DATABASECHANGELOG ", nativeQuery = true)
	public List<String> selectFromDatabasechangelog();
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "SELECT * FROM databasechangeloglock ", nativeQuery = true)
	public List<String> selectFromDatabasechangeloglock();

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE databasechangeloglock set locked=false ", nativeQuery = true)
	public void desbloquear();
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE databasechangeloglock set locked=true", nativeQuery = true)
	public void bloquear();

	//public List<String> getCampos(String table);




}