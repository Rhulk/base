package com.alquiler.reservas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class In18Controller {

	@GetMapping("/in18")
	public String index() {
		return "in18";
	}
}
