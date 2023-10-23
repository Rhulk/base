package com.alquiler.reservas.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alquiler.reservas.entity.Apunte;
import com.alquiler.reservas.entity.Curso;


@Repository
public interface ApunteRepository extends CrudRepository<Apunte, Long>{

	public Apunte findByIdAndCurso(Long apunte, Curso curso);

	

	
}
