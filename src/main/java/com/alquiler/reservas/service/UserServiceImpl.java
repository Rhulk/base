package com.alquiler.reservas.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alquiler.reservas.Exception.CustomeFieldValidationException;
import com.alquiler.reservas.entity.ChangePasswordForm;
import com.alquiler.reservas.entity.Databasechangeloglock;
import com.alquiler.reservas.entity.User;
import com.alquiler.reservas.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	//Asegurate de tener este autowired al inicio de la clase
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	EntityManager em;
	
	
	public Iterable getAllUsers(){
		return userRepository.findAll();
	}
	
	private boolean checkUsernameAvailable(User user) throws Exception {

		if (!userRepository.findByUsername(user.getUsername()).isEmpty()) {
			throw new CustomeFieldValidationException("Username no disponible","username");
			//throw new Exception("Username no disponible");
		}
		return true;
	}

	private boolean checkPasswordValid(User user) throws Exception {
	/*
		if ( !user.getPassword().equals(user.getConfirmPassword())) {
			//throw new Exception("Password y Confirm Password no son iguales");
			throw new CustomeFieldValidationException("Confirm Password es obligatorio","confirmPassword");
		}
	*/
		if (user.getConfirmPassword() == null || user.getConfirmPassword().isEmpty()) {
			throw new CustomeFieldValidationException("Confirm Password es obligatorio","confirmPassword");
		}		
		
		if ( !user.getPassword().equals(user.getConfirmPassword())) {
			throw new CustomeFieldValidationException("Password y Confirm Password no son iguales","password");
		}		
		
		return true;
	}


	@Override
	public User createUser(User user) throws Exception {
		if (checkUsernameAvailable(user) && checkPasswordValid(user)) {
			String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			System.out.println(" -- user -- " + user.toString());
			user = userRepository.save(user);
		}
		System.out.println(" [User Create]");
		return user;
	}
	
	@Override
	public User getUserById(Long id) throws Exception {
		User user = userRepository.findById(id).orElseThrow(() -> new Exception("User does not exist"));
		return user;
	}	
	
	@Override
	public User getUserByName(String username) throws Exception  {
		List<User> uu = userRepository.findByUsername(username);
		return uu.get(uu.size()-1);
	}
	
	/*
	 *  Update User
	 *  
	 */
	@Override
	@PreAuthorize("isAuthenticated()")  
	public User updateUser(User fromUser)  throws Exception {
		User toUser = getUserById(fromUser.getId());
		mapUser(fromUser, toUser);
		return userRepository.save(toUser);
	} 
	
	/*
	 *  For Active user
	 * 
	 * 	In delop proges
	 */
	public User modStatusUser(Long id, int status) throws Exception {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new Exception("UsernotFound in modStatusUser -"+this.getClass().getName()));
		user.setStatus(status);
		userRepository.save(user);
		return null;

	}
	
	//@PreAuthorize("hasAnyRole('ad')") old
	//@PreAuthorize("hasAuthority('ad')") not working
	public void deleteUser(Long id) throws Exception {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new Exception("UsernotFound in deleteUser -"+this.getClass().getName()));

		userRepository.delete(user);
	}
	
	public User changePassword(ChangePasswordForm form) throws Exception{
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		User storedUser = userRepository
				.findById( form.getId() )
				.orElseThrow(() -> new Exception("UsernotFound in ChangePassword -"+this.getClass().getName()));
		
		if ( !isLoggedUserADMIN() && !storedUser.getPassword().equals(form.getCurrentPassword())) {
			throw new Exception ("Current Password invalido.");
		}
		
		if( form.getCurrentPassword().equals(storedUser.getPassword())) {
			throw new Exception("Current Password Incorrect.");
		}
		
		if ( form.getCurrentPassword().equals(form.getNewPassword())) {
			throw new Exception("New Password must be different than Current Password!");
		}
		
		if( !form.getNewPassword().equals(form.getConfirmPassword())) {
			throw new Exception("New Password and Confirm Password does not match!");
		}
		
		 // se almacena la pass encriptada.
		//storedUser.setPassword(form.getNewPassword());
		String encodePassword = bCryptPasswordEncoder.encode(form.getNewPassword());
		storedUser.setPassword(encodePassword);
		return userRepository.save(storedUser);
	}	
	
	
	
	
	private boolean isLoggedUserADMIN() {
		//Obtener el usuario logeado
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		UserDetails loggedUser = null;
		Object roles = null;

		//Verificar que ese objeto traido de sesion es el usuario
		if (principal instanceof UserDetails) {
			loggedUser = (UserDetails) principal;

			roles = loggedUser.getAuthorities().stream()
					.filter(x -> "ad".equals(x.getAuthority())).findFirst()
					.orElse(null); 
		}
		return roles != null ? true : false;
	}
	
	private User getLoggedUser() throws Exception {
		//Obtener el usuario logeado
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		UserDetails loggedUser = null;

		//Verificar que ese objeto traido de sesion es el usuario
		if (principal instanceof UserDetails) {
			loggedUser = (UserDetails) principal;
		}
		
		List<User> myUser = userRepository.findByUsername(loggedUser.getUsername());

		return myUser.get(0);
	}
	
	/**
	 * Map everythin but the password. Without password problem with validation @NotBlank in field confirm password
	 * @param from
	 * @param to
	 */
	protected void mapUser(User from,User to) {
		to.setUsername(from.getUsername());
		to.setFirstName(from.getFirstName());
		to.setLastName(from.getLastName());
		to.setEmail(from.getEmail());
		to.setRoles(from.getRoles());		
		to.setPassword(from.getPassword());
		to.setConfirmPassword(from.getConfirmPassword());	
	}

	@Override
	public List<String> getAllTablas() {
		return userRepository.getTablas();
	}

	@Override
	public List<String> selectDataBaseChangelog(){
		return userRepository.selectFromDatabasechangelog();
	}
	
	@Override
	public Databasechangeloglock selectDataBaseChangeloglock(){
		List<String> listado = userRepository.selectFromDatabasechangeloglock();
		Databasechangeloglock databasechangeloglock = new Databasechangeloglock();
		databasechangeloglock.setID(Integer.valueOf(listado.get(0)));
		databasechangeloglock.setLOCKED(Boolean.parseBoolean(listado.get(1)));
		databasechangeloglock.setLOCKEDBY(listado.get(2));
		databasechangeloglock.setLOCKGRANTED(listado.get(2));
	
		return databasechangeloglock;
	}

	@Override
	public void desbloqueo() {
		userRepository.desbloquear();
		
	}

	@Override
	public void bloqueo() {
		userRepository.bloquear();
	}
}
