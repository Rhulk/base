package com.alquiler.reservas.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.MethodOrderer.Random;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.boot.test.context.SpringBootTest;

import com.alquiler.reservas.entity.Capitulo;
import com.alquiler.reservas.entity.Curso;
import com.alquiler.reservas.repository.CursoRepository;

import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;

import static org.mockito.ArgumentMatchers.*;

import java.util.List;
import java.util.Optional;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

@SpringBootTest
public class CursoServiceImpTest {
	
	
	@Mock 
	CursoRepository cursoRepository;
	  
	@InjectMocks
	CursoServiceImp cursoServiceImp;
	 
	
	// Test unitarios de getCurso
	@Test
	void getCursoExceptionTest() throws Exception  {
		
		Exception exception = assertThrows(Exception.class, () -> cursoServiceImp.getCurso(1));
		assertEquals("Curso does not exist", exception.getMessage());
		  
	}
	@Test
	void getCursoTest() throws Exception  {
		
		  Mockito.when(cursoRepository.findById( 3  ) ).thenReturn(getCursoOptional());
		  assertNotNull(cursoServiceImp.getCurso(3));	
	}
	
	// Test getCapitulos
	@Test
	void getCapitulosTest() {
		 assertNotNull(cursoServiceImp.getCapitulos(getCurso()));
	}
	
	@Test
	void getCapitulosNullTest() {
		 assertNull(cursoServiceImp.getCapitulos(new Curso()));
	}
	
	// Test getApartados
	
	@Test
	void getApartadosNullTest() {
		
		assertNull(cursoServiceImp.getApartados(new Curso()));
	}

	// simular retornos
	public Optional<Curso> getCursoOptional() {
		EasyRandom generator = new EasyRandom();
		return Optional.ofNullable(generator.nextObject(Curso.class));
	}
	public Curso getCurso(){
		EasyRandom generator = new EasyRandom();
		return generator.nextObject(Curso.class);
	}

	
}
