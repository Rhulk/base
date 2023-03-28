package com.alquiler.reservas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TodoController {
	
	@GetMapping("/listTodo")
	public String listTodo() {
		
		return "listTodo";
	}

}
