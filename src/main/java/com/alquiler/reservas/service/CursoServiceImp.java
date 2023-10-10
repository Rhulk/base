package com.alquiler.reservas.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
import com.alquiler.reservas.repository.ApartadoRepository;
import com.alquiler.reservas.repository.ApunteRepository;
import com.alquiler.reservas.repository.CursoRepository;

@Service
public class CursoServiceImp implements CursoService {

	@Autowired
	CursoRepository cursoRepository;
	
	@Autowired
	ApunteRepository apunteRepository;
	
	@Autowired
	ApartadoRepository apartadoRepository;
	
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

	public Apunte getApunteById(Long apunte) {
		Apunte apu = new Apunte();
		try {
			apu = apunteRepository.findById(apunte).orElseThrow(() -> new Exception("Apunte does not exist"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return apu;
	}
	
	public void deleteApunte(Apunte apu) {
		
		apunteRepository.delete(apu);
		
	}

	@Override
	public void createNewAporte(Long apartado, String notas, Long idUser) {
		Apartado apa = new Apartado();
		try {
			apa = apartadoRepository.findById(apartado)
					.orElseThrow(() -> new Exception("Apartado does not exist"));
		} catch (Exception e) {
			System.out.println(" Id apartado: "+apartado);
			e.printStackTrace();
		}
		
		apunteRepository.save(new Apunte(notas,apa,idUser));
		
		
	}
	
	

}
