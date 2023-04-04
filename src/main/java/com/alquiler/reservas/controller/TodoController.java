package com.alquiler.reservas.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.alquiler.reservas.entity.Estado;
import com.alquiler.reservas.service.TodoService;

@Controller
public class TodoController {
	
	@Autowired
	TodoService todoService;
	
	@GetMapping("/todolist")
	public String listTodo(Model model) {
		List<Estado> estados = new LinkedList<Estado>();
		
		estados.add(Estado.Inicial);
		
		System.out.println(estados.get(0).toString());
		System.out.println("Listado de To-dos Up");
		System.out.println(todoService.getByEstado(Estado.EnProceso));
		model.addAttribute(todoService.getByEstado(Estado.EnProceso));
		return "todo/todo-list";
	}

}
