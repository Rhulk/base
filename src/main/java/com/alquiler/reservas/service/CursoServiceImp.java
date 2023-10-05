package com.alquiler.reservas.service;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.alquiler.reservas.entity.Apartado;
import com.alquiler.reservas.entity.Apunte;
import com.alquiler.reservas.entity.CamposAndTipos;
import com.alquiler.reservas.entity.Capitulo;
import com.alquiler.reservas.entity.Curso;
import com.alquiler.reservas.repository.CursoRepository;

@Service
public class CursoServiceImp implements CursoService {

	@Autowired
	CursoRepository cursoRepository;
	
	@Autowired
	CursoDAO cursoDAO;

	@Override
	public Curso getCurso(Long id) throws Exception {
		
		return cursoRepository.findById(id).orElseThrow(() -> new Exception("Curso does not exist"));
	}

	@Override
	public List<Capitulo> getCapitulos(Curso curso) {
		
		
		
		return curso.getCapitulos();
	}

	@Override
	public List<Apartado> getApartados(Curso curso) {
		List<Apartado> apartados = new LinkedList<>();
		List<Capitulo> capitulos = new LinkedList<>();

		
		capitulos = getCapitulos(curso);
		
		for(int i=0; i<capitulos.size(); i++) {
			apartados.addAll(capitulos.get(i).getApartados());
			
		}
		return apartados;
	}

	@Override
	public List <Apunte> getApunte(Long apartado, int pag) {
		
		return cursoDAO.getApunteDAO(apartado,pag);
	}
	
	public int getCantidadAportesByApartado(Long apartado) {
		
		return cursoDAO.getCantidadAportesByApartadoDAO(apartado);
	}

	 

}
