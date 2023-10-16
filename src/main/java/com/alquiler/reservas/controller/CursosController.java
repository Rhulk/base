package com.alquiler.reservas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.alquiler.reservas.entity.Capitulo;
import com.alquiler.reservas.entity.Curso;
import com.alquiler.reservas.repository.CursoRepository;
import com.alquiler.reservas.service.CursoService;

@Controller
public class CursosController {
	
	@Autowired
	CursoService cursoService;
	
	@Autowired
	CursoRepository cursoRepository;
	
	
	@GetMapping("/cursoAlta")
	public String cursoAlta(Model model) {
		
		// Gestión de la activación de los formularios
		model.addAttribute("activoFormTodo",false);
		model.addAttribute("activoFormUser",false);
		model.addAttribute("activoFormCurso", false);
		
		// Gestión de tab acvitos o no
		model.addAttribute("tabTodo","no");
		model.addAttribute("listTabTodo","no");
		model.addAttribute("tabUser", "no");
		model.addAttribute("listTabUser","no");
		model.addAttribute("detailTabCurso","no");
		model.addAttribute("tabCurso","active");
		model.addAttribute("listTabCurso","no");
		model.addAttribute("misTabCurso","no");
		model.addAttribute("editarTabCurso","no");
		model.addAttribute("altaTabCurso","active");
		
		// modelo de datos 
		

// TO-DO: Crear el formulario del alta
		
		
		// TEST

		
		return "security/user-form/main-view.html";
	}			

	@GetMapping("/cursolistEdit")
	public String cursolistEdit(Model model) {
		
		// Gestión de la activación de los formularios
		model.addAttribute("activoFormTodo",false);
		model.addAttribute("activoFormUser",false);
		model.addAttribute("activoFormCurso", false);
		
		// Gestión de tab acvitos o no
		model.addAttribute("tabTodo","no");
		model.addAttribute("listTabTodo","no");
		model.addAttribute("tabUser", "no");
		model.addAttribute("listTabUser","no");
		model.addAttribute("detailTabCurso","no");
		model.addAttribute("tabCurso","active");
		model.addAttribute("listTabCurso","no");
		model.addAttribute("misTabCurso","no");
		model.addAttribute("editarTabCurso","active");
		model.addAttribute("altaTabCurso","no");
		
		// modelo de datos 
		model.addAttribute("cursos",cursoRepository.findAll());

// TO-DO: Crear el formulario de edición del curso y su re dirección
		
		// TEST

		
		return "security/user-form/main-view.html";
	}		
	
	@GetMapping("/cursolistPropios")
	public String cursolistPropios(Model model) {
		
		// Gestión de la activación de los formularios
		model.addAttribute("activoFormTodo",false);
		model.addAttribute("activoFormUser",false);
		model.addAttribute("activoFormCurso", false);
		
		// Gestión de tab acvitos o no
		model.addAttribute("tabTodo","no");
		model.addAttribute("listTabTodo","no");
		model.addAttribute("tabUser", "no");
		model.addAttribute("listTabUser","no");
		model.addAttribute("detailTabCurso","no");
		model.addAttribute("tabCurso","active");
		model.addAttribute("listTabCurso","no");
		model.addAttribute("misTabCurso","active");
		model.addAttribute("editarTabCurso","no");
		model.addAttribute("altaTabCurso","no");
		
		// modelo de datos Pendiente solo recuperar los del usuario
		model.addAttribute("cursos",cursoRepository.findAll());
		
		// TEST

		
		return "security/user-form/main-view.html";
	}	
	
	@GetMapping("/cursolist")
	public String cursoList(Model model) {
		
		// Gestión de la activación de los formularios
		model.addAttribute("activoFormTodo",false);
		model.addAttribute("activoFormUser",false);
		model.addAttribute("activoFormCurso", false);
		
		// Gestión de tab acvitos o no
		model.addAttribute("tabTodo","no");
		model.addAttribute("listTabTodo","no");
		model.addAttribute("tabUser", "no");
		model.addAttribute("listTabUser","no");
		model.addAttribute("detailTabCurso","no");
		model.addAttribute("tabCurso","active");
		model.addAttribute("listTabCurso","active");
		model.addAttribute("misTabCurso","no");
		model.addAttribute("editarTabCurso","no");
		model.addAttribute("altaTabCurso","no");
		
		// modelo de datos
		model.addAttribute("cursos",cursoRepository.findAll());
		
		// TEST

		
		return "security/user-form/main-view.html";
	}
	
	@GetMapping("/curso{id}")
	public String cursoDetalleID(Model model, @PathVariable(name="id") Long id ) throws Exception {
		
		Curso curso = cursoService.getCurso(id);

		// Gestión de la activación de los formularios
		model.addAttribute("activoFormTodo",false);
		model.addAttribute("activoFormUser",false);
		model.addAttribute("activoFormCurso", true);
		
		// Gestión de tab acvitos o no
		model.addAttribute("tabTodo","no");
		model.addAttribute("listTabTodo","no");
		model.addAttribute("tabUser", "no");
		model.addAttribute("listTabUser","no");
		model.addAttribute("tabCurso","active");
		model.addAttribute("listTabCurso","no");
		model.addAttribute("detailTabCurso","active");
		
		//Gestión de los objetos que se mandan a la vista.
		model.addAttribute("detallecurso", curso);
		model.addAttribute("capitulos", curso.getCapitulos());
		model.addAttribute("nCap",curso.getCapitulos().size());
		model.addAttribute("apartados",cursoService.getApartados(curso));
		model.addAttribute("nApartados",cursoService.getApartados(curso).size());
		
		
		// TEST
		System.out.println("capitulos : "+cursoService.getCurso(id).getCapitulos());
		System.out.println("apartados : "+cursoService.getApartados(curso));
		
		System.out.println("nCapitulos : "+cursoService.getCurso(id).getCapitulos().size());
		System.out.println("nCapitulos2 : "+cursoService.getCapitulos(cursoService.getCurso(id)).size());
		

		
		return "security/user-form/main-view.html";
	}

}
