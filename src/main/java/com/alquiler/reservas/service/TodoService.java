package com.alquiler.reservas.service;

import java.util.List;

import javax.validation.Valid;

import com.alquiler.reservas.entity.Estado;
import com.alquiler.reservas.entity.Todo;

public interface TodoService {
	
	public List<Todo> getByEstado(Estado estado);
	public Todo getById(Long id) throws Exception;
	public void createTodo(Todo todo);
	public void updateTodo(Todo todo) throws Exception;
	public void deleteTodo(Long id) throws Exception;

}
