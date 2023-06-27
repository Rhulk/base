package com.alquiler.reservas.controller.rest;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alquiler.reservas.entity.Estado;
import com.alquiler.reservas.entity.Todo;
import com.alquiler.reservas.service.TodoService;

@RestController
public class TestControllerRest {
	
	@Autowired
	TodoService todoService;

	@GetMapping("/testRest")
	public List<Todo> testRest() {
		//List<Todo> listTodos = new LinkedList<Todo>();
		
		//return "Test rest controller: proyec reserva";
		return todoService.getByEstado(Estado.Activos);
	}
}
