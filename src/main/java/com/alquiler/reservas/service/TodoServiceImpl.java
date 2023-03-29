package com.alquiler.reservas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alquiler.reservas.entity.Estado;
import com.alquiler.reservas.entity.Todo;
import com.alquiler.reservas.repository.TodoRepository;

public class TodoServiceImpl implements TodoService {
	
	@Autowired
	TodoRepository todoRepository;

	@Override
	public List<Todo> getByEstado(Estado estado) {
		
		return todoRepository.findByEstado(estado);
	}

}
