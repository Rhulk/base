package com.alquiler.reservas.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alquiler.reservas.entity.Estado;
import com.alquiler.reservas.entity.Todo;

@Repository
public interface TodoRepository  extends CrudRepository<Todo, Long>{

	
	public List<Todo> findByEstado(Estado estado);
	
	
}
