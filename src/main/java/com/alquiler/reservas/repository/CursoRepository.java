package com.alquiler.reservas.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alquiler.reservas.entity.CategoriaCurso;
import com.alquiler.reservas.entity.Curso;



@Repository
public interface CursoRepository  extends CrudRepository<Curso, Integer>{

	public List<Curso> findByCategoriacurso(CategoriaCurso categoriacurso);
	public List<Curso> findByUrlimagen(String url); // Test no es necesario.
	public List<Curso> findByUrlicono(String url); // Test no es necesario.
	
	
}
