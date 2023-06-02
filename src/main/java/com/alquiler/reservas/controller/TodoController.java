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
		System.out.println("En construcción: Pendiente listPriority");
		model.addAttribute("userForm", new User()); // sobra futuro activoUser
		model.addAttribute("activoTodo",true);
		model.addAttribute("todoForm", new Todo(Estado.Inicial));
		model.addAttribute("todoformTab","active");
		model.addAttribute("tipos",genericos.getAllTipos());
		
		return "security/user-form/main-view";
	}
	@PostMapping("/todoAlta")
	public String createTodo(@Valid @ModelAttribute("todoForm")Todo todo, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			
			model.addAttribute("userForm", new User()); // TODO: Remplazar para no cargar objeto de otro móudlo
			model.addAttribute("todoForm", new Todo(Estado.Inicial));
			model.addAttribute("todoformTab","active");
			model.addAttribute("tipos",genericos.getAllTipos());
			
		}else {
			
			todo.setEstado(Estado.Inicial);
			todoService.createTodo(todo);		

			// gestion de la visualización en main-view
			model.addAttribute("activoTodo",true);
			
			// objetos modulos
			model.addAttribute("userForm", new User()); // TODO: -> ocultar de main-view para no usar el objeto.
			model.addAttribute("todoForm", new Todo(Estado.Inicial));
			model.addAttribute("toDoList",todoService.getByEstado(Estado.Activos));
			
			// gestion visualización tab activo
			model.addAttribute("listTabUser","No");
			model.addAttribute("listTabSql","No");
			model.addAttribute("listTabToDo","active");
			model.addAttribute("todoformTab","no");
			
		}
	
		return "security/user-form/main-view"; // to-do > Cambio de nombre a main-view
	}
	
	@GetMapping("/todoAlta/Cancel")
	public String cancelarAlta(Model model) {
		return "redirect:/todolist";
	}
	
	@GetMapping("/todolist")
	public String listTodo(Model model) {
		List<Estado> estados = new LinkedList<Estado>();
		estados.add(Estado.Inicial);
		
		// Gestión del contenido de la pag
		model.addAttribute("roles",roleRepository.findAll());
		model.addAttribute("toDoList",todoService.getByEstado(Estado.Activos));
	
		// Gestión de tab acvitos o no
		model.addAttribute("listTabUser","No");
		model.addAttribute("listTabSql","No");
		model.addAttribute("listTabToDo","active");
		
		// Gestión de la activación de los formularios
		model.addAttribute("activoTodo",false);
		model.addAttribute("activoUser",false);
		
		return "security/user-form/main-view";	

	}
	/*
	 *  En construción
	 *  
	 *  TODO: Crear unPostMapping
	 * 
	 */
	@GetMapping("/modTodo/{id}")
	public String modTodo(Model model, @PathVariable(name="id") Long id ) throws Exception {

		// objetos modulos
		model.addAttribute("todoForm",todoService.getById(id));
		
		// tab activos
		model.addAttribute("listTabUser","No");
		model.addAttribute("listTabSql","No");
		model.addAttribute("todoformTab","active");
		
		// carga de modulos
		model.addAttribute("activoTodo",true);
		model.addAttribute("activoUser",false);
		model.addAttribute("editModeTodo",true);
		
		return "security/user-form/main-view"; 
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
