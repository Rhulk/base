package com.alquiler.reservas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alquiler.reservas.entity.Apartado;

@Repository
public interface ApartadoRepository extends CrudRepository<Apartado, Long>{

	
	
}
