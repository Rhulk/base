package com.alquiler.reservas.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.alquiler.reservas.entity.Apunte;
import com.alquiler.reservas.service.CursoService;

@RestController
public class CursosRest {
	
	@Autowired
	CursoService cursoService;
	
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
		Long idUser =(long) 7;
		try {
			cursoService.createNewAporte(apartado,notas,idUser);
		}catch (Exception e) {
			return "KO";
		}
		return "OK";
	}	
	
}
