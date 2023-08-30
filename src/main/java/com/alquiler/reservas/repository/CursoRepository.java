package com.alquiler.reservas.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alquiler.reservas.entity.CategoriaCurso;
import com.alquiler.reservas.entity.Curso;


@Repository
public interface CursoRepository  extends CrudRepository<Curso, Long>{

	
	public List<Curso> findByCategoriaCurso(CategoriaCurso categoriaCurso);
	
}
