package com.alquiler.reservas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	
	@GetMapping("/")
	public String mostarHome() {
		return "home";
	}
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
	
	@GetMapping("/admin/dashboard")
	public String dashboardAdmin() {
		return "dashboardAmin";
	}

	@GetMapping("/user/dashboard")
	public String dashboardUser() {
		return "dashboardUser";
	}
	
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/index")
	public String index() {
		return "index";
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
