package com.alquiler.reservas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alquiler.reservas.entity.Apunte;

@Repository
public interface ApunteRepository extends CrudRepository<Apunte, Long>{

	
}
