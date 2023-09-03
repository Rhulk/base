package com.alquiler.reservas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alquiler.reservas.entity.Curso;
import com.alquiler.reservas.repository.CursoRepository;

@Service
public class CursoServiceImp implements CursoService {

	@Autowired
	CursoRepository cursoRepository;

	@Override
	public Curso getCurso(Long id) throws Exception {
		
		return cursoRepository.findById(id).orElseThrow(() -> new Exception("Curso does not exist"));
	}
	
	

	 

}
