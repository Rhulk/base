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
	
	@GetMapping("/desbloquear")
	public String desbloqueo() {
		
		if(sqlService.desbloqueo()) {
			return "Desbloqueado";
		}
		return "Error";
	}
	
	@GetMapping("/admin")
	public String index(Model model) {
		model.addAttribute("userForm", new User());
		model.addAttribute("listTabUser","No");
		model.addAttribute("listTabSql","No");
		model.addAttribute("listTabToDo","No");
		model.addAttribute("listTabAdmin","active");
		return "security/user-form/user-view";
	}
	
	@GetMapping("/sql")
	public String home(Model model) {
		model.addAttribute("userForm", new User());
		model.addAttribute("roles",roleRepository.findAll());
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("listTab","active");
		System.out.println(" -- Tablas: "+userService.getAllTablas().toString());
		System.out.println(" -- Check Liquibase log -- "+userService.selectDataBaseChangelog().toString());
		
		//userService.bloqueo();
		
		System.out.println(" -- Check Liquibase block -- "+userService.selectDataBaseChangeloglock().toString());
		//userService.desbloqueo();
		System.out.println(" -- Check Liquibase block -- "+userService.selectDataBaseChangeloglock().toString());
		//sqlService.getCampos("SELECT COLUMN_NAME, DATA_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME ='user'");
		System.out.println(" Campos de la table User: "+ sqlService.getCampos("usuario").toString());
		System.out.println(" Campos de la table databasechangeloglock: "+ sqlService.getCampos("databasechangeloglock").toString());
		System.out.println(" Campos de la table databasechangelog: "+ sqlService.getCampos("databasechangelog").toString());
		//sqlService.desbloqueo();
		//userService.getFieldByTable("user");
		//return "security/user-form/user-view";
		return "security/user-form/user-list-mtn";
		//return "home";
	}
	
	@GetMapping("/log")
	public String logLiquibase(Model model) {
		model.addAttribute("logList",userService.selectDataBaseChangelog());
		return "mtn/liquibase-log-mtn";
	}
	
	@GetMapping("/lock")
	public String lockLiquibase(Model model) {
		model.addAttribute("lock",userService.selectDataBaseChangeloglock());
		return "mtn/liquibase-lock-mtn";
	}
	
	@GetMapping("/defaultUser")
	public String defaultUsert(Model model ) throws Exception {
		System.out.println(" Add User Default ");
		
		User user = new User();
		user.setApellido2("user");
		user.setConfirmPassword("123");
		user.setDireccion("user");
		user.setEmail("user"+"@email.com");
		user.setFirstName("user");
		user.setLastName("user");
		user.setMunicipio("user");
		user.setObservaciones("user");
		user.setPassword("123");
		
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
		user.setUsername("user");
		
		userService.createUser(user);
		//System.out.println(" User create: "+ user.toString());
		
		// sent view
		model.addAttribute("userList", userService.getAllUsers());
		
		
		return "security/user-form/user-list-mtn";
	}
	
	@GetMapping("/addUser/{clave}")
	public String addUser(@PathVariable(name="clave") String clave, Model model) throws Exception {
		System.out.println(" Add User "+clave);
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
		//System.out.println(" User create: "+ user.toString());
		
		// sent view
		model.addAttribute("userList", userService.getAllUsers());
		
		return "security/user-form/user-list-mtn";
	}
	
	@GetMapping("/generadorsql")
	public String generadorConsultas() {
		System.out.println("GeneradorSQL");
		
		return "mtn/sql/generadorsql";
	}
}
