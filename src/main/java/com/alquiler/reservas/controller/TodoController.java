package com.alquiler.reservas.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.alquiler.reservas.entity.Estado;
import com.alquiler.reservas.entity.Todo;
import com.alquiler.reservas.entity.User;
import com.alquiler.reservas.repository.RoleRepository;
import com.alquiler.reservas.service.TodoService;
import com.alquiler.reservas.service.UserService;

@Controller
public class TodoController {
	
	@Autowired
	TodoService todoService;
	
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired 
	UserService userService;
	
	@GetMapping("/todoAlta")
	public String altaTodo(Model model) {
		System.out.println("En construcción: pendiente Objeto: todoForm");
		model.addAttribute("userForm", new User()); // sobra
		model.addAttribute("todoForm", new Todo());
		model.addAttribute("todoformTab","active");
		return "security/user-form/user-view";
	}
	
	@GetMapping("/todolist")
	public String listTodo(Model model) {
		List<Estado> estados = new LinkedList<Estado>();
		
		estados.add(Estado.Inicial);
		
		System.out.println(estados.get(0).toString());
		System.out.println("Listado de To-dos Up");
		System.out.println(todoService.getByEstado(Estado.EnProceso));
		
		model.addAttribute("todoForm", new Todo());
		model.addAttribute("userForm", new User());
		model.addAttribute("roles",roleRepository.findAll());
		model.addAttribute("userList", userService.getAllUsers());
		
		model.addAttribute("toDoList",todoService.getByEstado(Estado.EnProceso));
	
		model.addAttribute("listTabUser","No");
		model.addAttribute("listTabSql","No");
		model.addAttribute("listTabToDo","active");
		
		return "security/user-form/user-view";
		//return "todo/todo-list";
	}

	@GetMapping("/userForm_test")
	public String getUserForm(Model model) {
		model.addAttribute("userForm", new User());
		//model.addAttribute("roles",roleRepository.findAll());
		//model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("listTab","active");
		model.addAttribute("listTabSql","SQL");
		model.addAttribute("listTabToDo","ToDo");
		return "security/user-form/user-view";
	}
}
