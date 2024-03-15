package com.alquiler.reservas.controller;

import java.util.List;
import java.util.Optional;

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

import com.alquiler.reservas.entity.Capitulo;
import com.alquiler.reservas.entity.Curso;
import com.alquiler.reservas.entity.Estado;
import com.alquiler.reservas.entity.Todo;
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

		return "security/user-form/main-view.html";
	}	
	@PostMapping("/cursoAlta")
	public String createCurso(@Valid @ModelAttribute("detallecurso")Curso curso, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			
			model.addAttribute("detallecurso", curso);
			//model.addAttribute("tipos",genericos.getAllTipos());
			//model.addAttribute("prioridades", genericos.getAllPrioridades());
			//model.addAttribute("estados",genericos.getEstadosSelected());
			
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
			
			// Gestión de la activación de los formularios
			model.addAttribute("activoFormTodo",false);
			model.addAttribute("activoFormUser",false);
			model.addAttribute("activoFormCurso", false);
			
			// TEST
			System.out.println("Error: "+result.getAllErrors());
			
		}else {
			// TEST
			System.out.println("Save Curso");
			//todoService.createTodo(todo);		
				
			// gestion de la visualización en main-view
			//model.addAttribute("activoFormTodo",true);
			
			// objetos modulos
				//model.addAttribute("toDoList",todoService.getByEstado(Estado.Activos));
			
			// gestion visualización tab act
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
				
		}
	
		return "security/user-form/main-view"; 
	}

	@GetMapping("/cursolistEdit/{id}")
	public String cursolistEdit(Model model,
			@PathVariable(name="id") Integer id
			) throws Exception {
		
		Curso curso = cursoService.getCurso(id);
		
		// Gestión de la activación de los formularios
		model.addAttribute("activoFormTodo",false);
		model.addAttribute("activoFormUser",false);
		model.addAttribute("activoFormCurso", false);
		
		// Gestion del edit o  del dashboard
		model.addAttribute("activoDashboardCurso", false);
		model.addAttribute("activoEditarCurso", true);
		
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
		model.addAttribute("editarcurso",curso );
		model.addAttribute("capitulos", curso.getCapitulos());
		model.addAttribute("apartados",cursoService.getApartados(curso));
		
		System.out.println("apartados "+cursoService.getApartados(curso));
		System.out.println("capitulos " +curso.getCapitulos());
		
		// TEST
		
		System.out.println("apartados: "+cursoService.getApartados(curso));
		
		return "security/user-form/main-view.html";
	}	
	@GetMapping("/cursolistEdit")
	public String cursolistEdit(Model model
			) {
		
		// Gestión de la activación de los formularios
		model.addAttribute("activoFormTodo", false);
		model.addAttribute("activoFormUser", false);
		model.addAttribute("activoFormCurso", false);
		
		// Gestion del edit o  del dashboard
		model.addAttribute("activoDashboardCurso", true);
		model.addAttribute("activoEditarCurso", false);
		
		//Gestion de los btn editar o detalle
		model.addAttribute("detalleCurso", "oculto");
		model.addAttribute("editarCurso", "");		
		
		
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
		model.addAttribute("cursos",cursoRepository.findAll() );


		
		return "security/user-form/main-view.html";
	}	
	

	
	@GetMapping("/cursolistPropios")
	public String cursolistPropios(Model model) {
		
		// Gestión de la activación de los formularios
		model.addAttribute("activoFormTodo",false);
		model.addAttribute("activoFormUser",false);
		model.addAttribute("activoFormCurso", false);
		
		// Gestion del edit o  del dashboard
		model.addAttribute("activoDashboardCurso", false);
		model.addAttribute("activoEditarCurso", false);
		
		//Gestion de los btn editar o detalle
		model.addAttribute("detalleCurso", "");
		model.addAttribute("editarCurso", "oculto");	
		
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
		model.addAttribute("cursos",cursoService.getCursosByLoguinUser());
		
		// TEST

		
		return "security/user-form/main-view.html";
	}	
	
	@GetMapping("/cursolist")
	public String cursoList(Model model) {
		
		// Gestión de la activación de los formularios
		model.addAttribute("activoFormTodo",false);
		model.addAttribute("activoFormUser",false);
		model.addAttribute("activoFormCurso", false);
		
		// Gestion del edit o  del dashboard
		model.addAttribute("activoDashboardCurso", false);
		model.addAttribute("activoEditarCurso", false);
		
		//Gestion de los btn editar o detalle
		model.addAttribute("detalleCurso", "");
		model.addAttribute("editarCurso", "oculto");
		
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
	public String cursoDetalleID(Model model, @PathVariable(name="id") Integer id ) throws Exception {
		
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
