package com.alquiler.reservas.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alquiler.reservas.entity.Role;

import com.alquiler.reservas.repository.UserRepository;



@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
    UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		//Buscar nombre de usuario en nuestra base de datos   //com.alquiler.reservas.entity.User appUser = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User does not exist!"));
		List<com.alquiler.reservas.entity.User> appUser = userRepository.findByUsername(username);
		
	    Set grantList = new HashSet(); 
	    
	    //Crear la lista de los roles/accessos que tienen el usuarios
	    for (Role role: ((com.alquiler.reservas.entity.User) appUser).getRoles()) {
	        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getDescripcion());
	            grantList.add(grantedAuthority);
	    }
			
	    //Crear y retornar Objeto de usuario soportado por Spring Security
	    UserDetails user = (UserDetails) new User(appUser.get(0).getUsername(), appUser.get(0).getPassword(), grantList);
	    return user;
		
	}

}
