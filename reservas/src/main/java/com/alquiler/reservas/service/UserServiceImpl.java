package com.alquiler.reservas.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alquiler.reservas.entity.ChangePasswordForm;
import com.alquiler.reservas.entity.User;
import com.alquiler.reservas.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	public Iterable getAllUsers(){
		return userRepository.findAll();
	}
	
	private boolean checkUsernameAvailable(User user) throws Exception {

		if (!userRepository.findByUsername(user.getUsername()).isEmpty()) {
			throw new Exception("Username no disponible");
		}
		return true;
	}

	private boolean checkPasswordValid(User user) throws Exception {
		if ( !user.getPassword().equals(user.getConfirmPassword())) {
			throw new Exception("Password y Confirm Password no son iguales");
		}
		return true;
	}


	@Override
	public User createUser(User user) throws Exception {
		if (checkUsernameAvailable(user) && checkPasswordValid(user)) {
			user = userRepository.save(user);
		}
		return user;
	}
	
	@Override
	public User getUserById(Long id) throws Exception {
		User user = userRepository.findById(id).orElseThrow(() -> new Exception("User does not exist"));
		return user;
	}	
	
	@PreAuthorize("hasAnyRole('ad','cl')")
	@Override
	public User updateUser(User fromUser)  throws Exception {
		User toUser = getUserById(fromUser.getId());
		mapUser(fromUser, toUser);
		return userRepository.save(toUser);
	}
	
	@PreAuthorize("hasAnyRole('ad')")
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
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails loggedUser = null;
		if (principal instanceof UserDetails) {
			loggedUser = (UserDetails) principal;

			loggedUser.getAuthorities().stream()
					.filter(x -> "ad".equals(x.getAuthority() ))      
					.findFirst().orElse(null); //loggedUser = null;
		}
		return loggedUser != null ?true :false;
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
	
}
