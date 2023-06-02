package com.alquiler.reservas.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alquiler.reservas.entity.Estado;
import com.alquiler.reservas.entity.Todo;
import com.alquiler.reservas.entity.User;
import com.alquiler.reservas.repository.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	TodoRepository todoRepository;

	@Override
	public List<Todo> getByEstado(Estado estado) {
		List<Todo> todos = new LinkedList<Todo>();
		if (estado.getDescripcion().equals("Todos menos resuelto")) {
			todos = todoRepository.findByEstado(Estado.Inicial);
			todos.addAll(todoRepository.findByEstado(Estado.EnProceso));
			todos.addAll(todoRepository.findByEstado(Estado.Pausado));
			
			return todos;
			
		}else {
			if (estado.getDescripcion().equals("Recupera todos")) {
				todos = todoRepository.findByEstado(Estado.Inicial);
				todos.addAll(todoRepository.findByEstado(Estado.EnProceso));
				todos.addAll(todoRepository.findByEstado(Estado.Pausado));
				todos.addAll(todoRepository.findByEstado(Estado.Resuelto));
				return todos;
			}else {
				return todoRepository.findByEstado(estado);
			}
		}
	}

	@Override
	public void createTodo(Todo todo) {
		Todo doneTodo = todoRepository.save(todo);
	}

	@Override
	public Todo getById(Long id) throws Exception{
		// TODO Auto-generated method stub
		
		Todo todo = todoRepository.findById(id).orElseThrow(() -> new Exception("Todo does not exist"));
		return todo;
	}

}
