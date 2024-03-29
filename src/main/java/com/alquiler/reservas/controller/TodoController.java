package com.alquiler.reservas.controller;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.alquiler.reservas.entity.Estado;
import com.alquiler.reservas.entity.Todo;
import com.alquiler.reservas.entity.User;
import com.alquiler.reservas.repository.RoleRepository;
import com.alquiler.reservas.service.TodoService;
import com.alquiler.reservas.service.UserService;
import com.alquiler.reservas.util.Genericos;

@Controller
public class TodoController {
	
	@Autowired
	TodoService todoService;
	
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired 
	UserService userService;
	
	Genericos genericos = new Genericos();
	
	@GetMapping("/todoAlta")
	public String altaTodo(Model model) {
		
		
		// continido pag 
		model.addAttribute("todoForm", new Todo(Estado.Inicial));
		model.addAttribute("tipos",genericos.getAllTipos());
		model.addAttribute("prioridades", genericos.getAllPrioridades());
		model.addAttribute("estados",genericos.getEstadosSelected());
		System.out.println(" Prioridades: "+genericos.getAllPrioridades());
		
		// tab activos
		model.addAttribute("tabTodo", "active" );
		model.addAttribute("formTabTodo","active");
		
		// Gestión de los formularios
		model.addAttribute("activoFormTodo",true);
		model.addAttribute("activoFormUser",false);
		model.addAttribute("activoFormCurso", false);
		
		return "security/user-form/main-view";
	}
	@PostMapping("/todoAlta")
	public String createTodo(@Valid @ModelAttribute("todoForm")Todo todo, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			
			model.addAttribute("todoForm", todo);
			model.addAttribute("tipos",genericos.getAllTipos());
			model.addAttribute("prioridades", genericos.getAllPrioridades());
			model.addAttribute("estados",genericos.getEstadosSelected());
			
			// tab activos
			model.addAttribute("tabTodo", "active" );
			model.addAttribute("formTabTodo","active");
			
			// Gestión de los formularios
			model.addAttribute("activoFormTodo",true);
			model.addAttribute("activoFormCurso", false);
			//model.addAttribute("activoFormUser",false);
			
		}else {

			todoService.createTodo(todo);		

			// gestion de la visualización en main-view
			//model.addAttribute("activoFormTodo",true);
			
			// objetos modulos
			model.addAttribute("toDoList",todoService.getByEstado(Estado.Activos));
			
			// gestion visualización tab act
			model.addAttribute("tabTodo","active");
			model.addAttribute("listTabTodo","active");
				
		}
	
		return "security/user-form/main-view"; 
	}
	
	@GetMapping("/todoAlta/Cancel")
	public String cancelarAlta(Model model) {
		return "redirect:/todolist";
	}
	
	@GetMapping("/todolist")
	public String listTodo(Model model) {

		
		// Gestión del contenido de la pag
		model.addAttribute("toDoList",todoService.getByEstado(Estado.Activos));
		model.addAttribute("tipos",genericos.getAllTipos());	
		System.out.println(" TEST: "+todoService.getByEstado(Estado.Activos));
		if (!todoService.getByEstado(Estado.Activos).isEmpty()) {
			
			System.out.println("TODO 0 :"+todoService.getByEstado(Estado.Activos).get(0));
		}
		
		// Gestión de tab acvitos o no
		model.addAttribute("tabTodo","active");
		model.addAttribute("listTabTodo","active");
		model.addAttribute("tabUser", "no");
		model.addAttribute("listTabUser","no");
		
		// Gestión de la activación de los formularios
		model.addAttribute("activoFormTodo",false);
		model.addAttribute("activoFormUser",false);
		model.addAttribute("activoFormCurso", false);
		
		//Control model delete 
		model.addAttribute("deleteTodo",true);
		model.addAttribute("deleteUser",false);
		
		return "security/user-form/main-view";	//TODO: Mejora de la ubicación y nombres mas descriptivos de las pag y las carpetas

	}

	@GetMapping("/modTodo/{id}")
	public String modTodo(Model model, @PathVariable(name="id") Long id ) throws Exception {

		// objetos modulos
		model.addAttribute("todoForm",todoService.getById(id));
		model.addAttribute("tipos",genericos.getAllTipos());
		model.addAttribute("estados",genericos.getEstadosSelected());
		model.addAttribute("prioridades", genericos.getAllPrioridades());
		
		// tab modulo y listados activos
		model.addAttribute("formTabTodo", "active");
		model.addAttribute("tabTodo","active");
		
		
		// carga de modulos
		model.addAttribute("activoFormTodo",true);
		model.addAttribute("activoFormUser",false);
		model.addAttribute("editModeTodo",true);
		
		return "security/user-form/main-view"; 
	}

	@PostMapping("/postModTodo")
	public String postModTodo(@Valid @ModelAttribute("todoForm") Todo todo,
			BindingResult result, ModelMap model) {
		if(result.hasErrors()) { // Return for error 
			// Contenido a mostrar en la pag
			model.addAttribute("todoForm",todo);
			model.addAttribute("tipos",genericos.getAllTipos());
			model.addAttribute("estados",genericos.getEstadosSelected());
			model.addAttribute("prioridades", genericos.getAllPrioridades());
			
			// tab activos
			model.addAttribute("formTabTodo","active");
			model.addAttribute("tabTodo", "active");
			
			// carga de modulos
			model.addAttribute("activoFormTodo",true);
			model.addAttribute("editModeTodo",true);
			model.addAttribute("activoFormCurso", false);
			
			return "security/user-form/main-view";
		}else {
			try {
				todoService.updateTodo(todo);
			}catch (Exception e) {
				System.out.println("\n Log: "+e.toString()+"\n");
			}	
		}
		
		return "redirect:/todolist";
	}
	@GetMapping("/deleteTodo/{id}")
	public String deleteTodo(Model model, @PathVariable(name="id") Long id) {
		
		try {
			todoService.deleteTodo(id);
		}catch (Exception e) {
			model.addAttribute("deleteError", "No se ha podido borrar el ToDo");
		}
		
		return "redirect:/todolist";
		
	}
	
	@GetMapping("/userForm_test")
	public String getUserForm(Model model) {
		model.addAttribute("userForm", new User());
		//model.addAttribute("roles",roleRepository.findAll());
		//model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("listTab","active");
		model.addAttribute("listTabSql","SQL");
		model.addAttribute("listTabToDo","ToDo");
		return "security/user-form/main-view";
	}
}
