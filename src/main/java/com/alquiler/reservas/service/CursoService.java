package com.alquiler.reservas.service;

import java.util.List;

import com.alquiler.reservas.entity.CategoriaCurso;
import com.alquiler.reservas.entity.Curso;

public interface CursoService {

	public List<Curso> getByCategoriaCurso(CategoriaCurso categoriaCurso);
	/* public List<Curso> getAll(); */
}
