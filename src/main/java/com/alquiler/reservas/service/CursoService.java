package com.alquiler.reservas.service;

import java.util.List;

import com.alquiler.reservas.entity.Apartado;
import com.alquiler.reservas.entity.Capitulo;
import com.alquiler.reservas.entity.CategoriaCurso;
import com.alquiler.reservas.entity.Curso;

public interface CursoService {

	 
	/* public List<Curso> getAll(); */
	
	public Curso getCurso(Long id) throws Exception;

	List<Capitulo> getCapitulos(Curso curso);
	
	List<Apartado> getApartads(Apartado apartado);
	
}
