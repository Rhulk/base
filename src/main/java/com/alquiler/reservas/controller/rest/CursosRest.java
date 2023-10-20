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
	
	@GetMapping("/apuntes/{apartado}/{pag}")
	public List <Apunte> getApunteByApartado(@PathVariable(name="apartado") Long apartado, @PathVariable(name="pag") int pag){
		
		return cursoService.getApunte(apartado,pag);
	}
	
	@GetMapping("/apuntes/{apartado}")
	public int getCantidadAportesByApartado(@PathVariable(name="apartado") Long apartado) {
		
		return cursoService.getCantidadAportesByApartado(apartado);
	}

	@GetMapping("/deleteapunte/{apunte}")
	public String deleteAporte(@PathVariable(name="apunte") Long apunte) {
		
		try {
			cursoService.deleteApunte(cursoService.getApunteById(apunte));
		}catch (Exception e) {
			return "KO";
		}
		return "OK";
	}
	
	@GetMapping("/saveAporteIn/{apartado}/{notas}")
	public String saveAporteIn(@PathVariable(name="apartado") Long apartado, @PathVariable(name="notas") String notas) {
		
		User usuarioActual = getLoguin();
		try {
			cursoService.createNewAporte(apartado,notas,usuarioActual.getId());
		}catch (Exception e) {
			return "KO";
		}
		return "OK";
	}	
	
	@GetMapping("/checkout/{apartado}/{check}")
	public String checkOut(@PathVariable(name="apartado") Long apartado, @PathVariable(name="check") boolean check ) {
		User usuarioActual = getLoguin();
		
		try {
			cursoService.checking(apartado,check,usuarioActual.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return "KO";
		}
		
		
		return "OK";
	}
	
	@GetMapping("/checkstatus/{apartado}")
	public Respuesta checkStatus(@PathVariable(name="apartado") Long apartado) {
		User usuarioActual = getLoguin();
		Respuesta res = new Respuesta();
		res.setCheck(cursoService.getCheckoutByApartadoAndUser(apartado, usuarioActual.getId()).isChecking());
		
		return res;
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
