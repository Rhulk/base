package com.alquiler.reservas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
