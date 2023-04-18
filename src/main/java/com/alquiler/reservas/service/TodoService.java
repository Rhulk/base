package com.alquiler.reservas.service;

import java.util.List;

import com.alquiler.reservas.entity.Estado;
import com.alquiler.reservas.entity.Todo;

public interface TodoService {
	
	public List<Todo> getByEstado(Estado estado);
	
	public void createTodo(Todo todo);

}
