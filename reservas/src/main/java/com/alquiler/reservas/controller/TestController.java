package com.alquiler.reservas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
/*	
	@GetMapping("/")
	public String mostarHome() {
		return "home";
	}
*/	
	@GetMapping("/test")
	public String test() {
		return "home";
	}
	

	/*
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/index")
	public String index() {
		return "home";
	}
	
*/

}
