package com.alquiler.reservas.repository;

import org.springframework.data.repository.CrudRepository;

import com.alquiler.reservas.entity.Todo;

public interface TodoRepository  extends CrudRepository<Todo, Long>{

}
