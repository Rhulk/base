package com.alquiler.reservas.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alquiler.reservas.entity.Capitulo;
import com.alquiler.reservas.entity.Curso;

@Repository
public interface CapituloRepository extends CrudRepository<Capitulo, Long> {

	List<Capitulo> findByCurso(Curso findCurso);

	
}
