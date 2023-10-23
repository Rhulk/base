package com.alquiler.reservas.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import com.alquiler.reservas.entity.Apunte;
import com.alquiler.reservas.entity.Respuesta;
import com.alquiler.reservas.entity.User;
import com.alquiler.reservas.service.CursoService;
import com.alquiler.reservas.service.UserService;

@RestController
public class CursosRest {
	
	@Autowired
	CursoService cursoService;
	
	@Autowired
	UserService usuarioService;
	
	@GetMapping("/apuntes/{apartado}/{pag}/{curso}")
	public List <Apunte> getApunteByApartado(
			@PathVariable(name="apartado") Long apartado,
			@PathVariable(name="pag") int pag,
			@PathVariable(name="curso") Long curso
			){
		
		return cursoService.getApunte(apartado,pag,curso);
	}
	
	@GetMapping("/apuntes/{apartado}/{curso}")
	public int getCantidadAportesByApartado(
			@PathVariable(name="apartado") Long apartado,
			@PathVariable(name="curso") Long curso			
			) {
		
		return cursoService.getCantidadAportesByApartadoAndCurso(apartado, curso);
	}

	@GetMapping("/deleteapunte/{apunte}/{curso}")
	public Respuesta deleteAporte(
			@PathVariable(name="apunte") Long apunte,
			@PathVariable(name="curso") Long curso
			) {
		
		return new Respuesta( cursoService.deleteApunte(cursoService.getApunteByIdAndCurso(apunte,curso)) );
	}
	
	@GetMapping("/saveAporteIn/{apartado}/{notas}/{curso}")
	public Respuesta saveAporteIn(
			@PathVariable(name="apartado") Long apartado,
			@PathVariable(name="notas") String notas,
			@PathVariable(name="curso") Long curso
			){

		return new Respuesta( 
				cursoService.createNewAporte(
						apartado,notas,getLoguin().getId(),curso) 
				);
	}
	
	@GetMapping("/modApunteIn/{apunte}/{notas}/{curso}")
	public Respuesta modApunteIn(
			@PathVariable(name="apunte") Long apunte, 
			@PathVariable(name="notas") String notas,
			@PathVariable(name="curso") Long curso
			) {

		return new Respuesta(cursoService.modApunte(apunte,notas,curso));
	}
	
	@GetMapping("/checkout/{apartado}/{check}/{curso}")
	public Respuesta checkOut(
			@PathVariable(name="apartado") Long apartado, 
			@PathVariable(name="check") boolean check,
			@PathVariable(name="curso") Long curso
			) {
		
		return new Respuesta( cursoService.checking(apartado,check,getLoguin().getId(),curso) );
	}
	
	@GetMapping("/checkstatus/{apartado}/{curso}")
	public Respuesta checkStatus(
			@PathVariable(name="apartado") Long apartado,
			@PathVariable(name="curso") Long curso
			) {
		
		return new Respuesta( cursoService.getCheckoutByApartadoAndUserAndCurso(apartado, getLoguin().getId(),curso).isChecking() );
	}
	
	@GetMapping("/follow/{curso}")
	public Respuesta follow(@PathVariable(name="curso") Long curso) {
		
		return new Respuesta(cursoService.followCurso(curso));
	}
	
	@GetMapping("/unfollow/{curso}")
	public Respuesta unfollow(@PathVariable(name="curso") Long curso) {
		
		return new Respuesta(cursoService.unfollowCurso(curso));
	}
	
	@GetMapping("/isfollow/{curso}")
	public Respuesta isfollow(@PathVariable(name="curso") Long curso) {
		
		return new Respuesta(cursoService.isFollowCurso(curso));
	}
	
	public User getLoguin() {
		User userLogado = new User();
		
	    Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
	    try {
	    	userLogado = this.usuarioService.getUserByName(userDetail.getUsername());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userLogado;
	}
	
	
}
