package com.alquiler.reservas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TodoController {
	
	@GetMapping("/todolist")
	public String listTodo() {
		System.out.println("Listado de To-dos");
		return "/todo/todo-list";
	}

}
