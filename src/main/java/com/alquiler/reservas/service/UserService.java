package com.alquiler.reservas.service;

import java.util.List;

import javax.transaction.Transactional;

import com.alquiler.reservas.entity.ChangePasswordForm;
import com.alquiler.reservas.entity.Databasechangeloglock;
import com.alquiler.reservas.entity.User;

public interface UserService {

	public Iterable getAllUsers();
	public User createUser(User formUser) throws Exception;
	public User getUserById(Long id) throws Exception;
	public User getUserByName(String name) throws Exception;
	public User modStatusUser(Long id , int status) throws Exception; // test
	public User updateUser(User user)throws Exception ;
	public void deleteUser(Long id) throws Exception;
	public User changePassword(ChangePasswordForm form) throws Exception;
	public List<String> getAllTablas();
	//public List<String> getField(String query);
	// liquibase
	public List<String> selectDataBaseChangelog();
	public Databasechangeloglock selectDataBaseChangeloglock();//Operativo pero sin datos.
	
	// no funciona
	public void desbloqueo();
	public void bloqueo();
	
	
}