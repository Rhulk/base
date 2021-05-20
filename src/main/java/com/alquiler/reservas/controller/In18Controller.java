package com.alquiler.reservas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class In18Controller {

	@GetMapping("/i18n")
	public String index() {
		return "i18n";
	}
}
