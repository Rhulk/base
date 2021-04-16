package com.alquiler.reservas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.alquiler.reservas.entity.User;
import com.alquiler.reservas.repository.RoleRepository;
import com.alquiler.reservas.repository.UserRepository;
import com.alquiler.reservas.service.UserService;


@Controller
public class UserController {

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired 
	UserService userService;
	
	@GetMapping("/userForm")
	public String getUserForm(Model model) {
		model.addAttribute("userForm", new User());
		model.addAttribute("roles",roleRepository.findAll());
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("listTab","active");
		return "security/user-form/user-view";
	}
	
	@GetMapping({"/","/login"})
	public String index() {
		return "index";
	}
	
	@GetMapping("/admin/dashboard")
	public String dashboardAdmin() {
		return "dashboardAmin";
	}

	@GetMapping("/user/dashboard")
	public String dashboardUser() {
		return "dashboardUser";
	}
	
	@GetMapping("/user")
	public String user() {
		return "security/user-form/user-view";
	}
	
	@GetMapping("/accessdenied")
	public String accessdenied() {
		return "accessdenied";
	}
}
