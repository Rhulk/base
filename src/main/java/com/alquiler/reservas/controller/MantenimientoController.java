package com.alquiler.reservas.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.alquiler.reservas.entity.Role;
import com.alquiler.reservas.entity.User;
import com.alquiler.reservas.repository.RoleRepository;
import com.alquiler.reservas.repository.UserRepository;
import com.alquiler.reservas.service.SqlService;
import com.alquiler.reservas.service.UserService;

@Controller
public class MantenimientoController {

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired 
	UserService userService;
	
	@Autowired
	SqlService sqlService;
	
	
	
	@GetMapping("/sql")
	public String home(Model model) {
		model.addAttribute("userForm", new User());
		model.addAttribute("roles",roleRepository.findAll());
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("listTab","active");
		System.out.println(" -- Tablas: "+userService.getAllTablas().toString());
		//sqlService.getCampos("SELECT COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME ='user'");
		System.out.println(" Campos de la table User: "+ sqlService.getCampos("usuario").toString());
		sqlService.desbloqueo();
		//userService.getFieldByTable("user");
		//return "security/user-form/user-view";
		return "security/user-form/user-list";
		//return "home";
	}
	
	@GetMapping("/addUser/{clave}")
	public String addUser(@PathVariable(name="clave") String clave, Model model) throws Exception {
		System.out.println(" Add User Default "+clave);
		User user = new User();
		user.setApellido2(clave);
		user.setConfirmPassword(clave);
		user.setDireccion(clave);
		user.setEmail(clave+"@email.com");
		user.setFirstName(clave);
		user.setLastName(clave);
		user.setMunicipio(clave);
		user.setObservaciones(clave);
		user.setPassword(clave);
		
		Role rol = roleRepository.findByName("cl");
		if ( rol == null) {
			rol = new Role();
			rol.setName("cl");
			rol.seteDscripcion("Rol Cliente");
			roleRepository.save(rol);
		}
		List <Role> roles = Arrays.asList(rol);
		//user.setRoles(roles); //Si no exite el Rol 
		user.setStatus(1);
		user.setTelefono("658451235");
		user.setUsername(clave);
		
		userService.createUser(user);
		
		
		// sent view
		model.addAttribute("userList", userService.getAllUsers());
		
		return "security/user-form/user-list";
	}
}
