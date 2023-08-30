package com.alquiler.reservas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.alquiler.reservas.repository.CursoRepository;
import com.alquiler.reservas.service.CursoService;

@Controller
public class CursosController {
	
	@Autowired
	CursoService cursoService;
	
	@Autowired
	CursoRepository cursoRepository;
	
	@GetMapping("/cursolist")
	public String cursoList(Model model) {
		
		// Gestión de la activación de los formularios
		model.addAttribute("activoFormTodo",false);
		model.addAttribute("activoFormUser",false);
		
		// Gestión de tab acvitos o no
		model.addAttribute("tabTodo","no");
		model.addAttribute("listTabTodo","no");
		model.addAttribute("tabUser", "no");
		model.addAttribute("listTabUser","no");
		model.addAttribute("detailTabCurso","no");
		model.addAttribute("tabCurso","active");
		model.addAttribute("listTabCurso","active");
		
		// modelo de datos
		
		System.out.println(" -- TEST 2 --"+cursoRepository.findAll());
		
		return "security/user-form/main-view.html";
	}
	
	@GetMapping("/curso")
	public String cursoDetalle(Model model) {

		// Gestión de la activación de los formularios
		model.addAttribute("activoFormTodo",false);
		model.addAttribute("activoFormUser",false);
		
		// Gestión de tab acvitos o no
		model.addAttribute("tabTodo","no");
		model.addAttribute("listTabTodo","no");
		model.addAttribute("tabUser", "no");
		model.addAttribute("listTabUser","no");
		model.addAttribute("tabCurso","active");
		model.addAttribute("listTabCurso","no");
		model.addAttribute("detailTabCurso","active");
		
		
		
		return "cursos/detalle.html";
	}
	
	@GetMapping("/cursoID")
	public String cursoDetalleID(Model model) {

		// Gestión de la activación de los formularios
		model.addAttribute("activoFormTodo",false);
		model.addAttribute("activoFormUser",false);
		
		// Gestión de tab acvitos o no
		model.addAttribute("tabTodo","no");
		model.addAttribute("listTabTodo","no");
		model.addAttribute("tabUser", "no");
		model.addAttribute("listTabUser","no");
		model.addAttribute("tabCurso","active");
		model.addAttribute("listTabCurso","no");
		model.addAttribute("detailTabCurso","active");
		
		
		
		return "security/user-form/main-view.html";
	}

}
