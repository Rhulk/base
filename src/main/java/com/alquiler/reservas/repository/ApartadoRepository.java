package com.alquiler.reservas.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alquiler.reservas.entity.Apartado;
import com.alquiler.reservas.entity.Capitulo;

@Repository
public interface ApartadoRepository extends CrudRepository<Apartado, Long>{


	List<Apartado> findByCapitulo(Capitulo cap);

	//public Apartado findByIdAndCurso(Long apartado, Long curso);
	
}
