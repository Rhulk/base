package com.alquiler.reservas.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.alquiler.reservas.entity.Apartado;
import com.alquiler.reservas.entity.Apunte;
import com.alquiler.reservas.entity.CamposAndTipos;
import com.alquiler.reservas.entity.Capitulo;
import com.alquiler.reservas.entity.Checkout;
import com.alquiler.reservas.entity.Curso;
import com.alquiler.reservas.entity.User;
import com.alquiler.reservas.repository.ApartadoRepository;
import com.alquiler.reservas.repository.ApunteRepository;
import com.alquiler.reservas.repository.CheckoutRepository;
import com.alquiler.reservas.repository.CursoRepository;
import com.alquiler.reservas.repository.UserRepository;

@Service
public class CursoServiceImp implements CursoService {

	@Autowired
	CursoRepository cursoRepository;
	
	@Autowired
	ApunteRepository apunteRepository;
	
	@Autowired
	ApartadoRepository apartadoRepository;
	
	@Autowired
	CheckoutRepository checkoutRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService usuarioService;
	
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

	@Override
	public void checking(Long apartado, boolean check, Long idUser) {
		Apartado apa = new Apartado();
		User uu = new User();
		Checkout cc = new Checkout();
		try {
			apa = apartadoRepository.findById(apartado)
					.orElseThrow(() -> new Exception("Apartado does not exist"));	
			uu = userRepository.findById(idUser)
					.orElseThrow(() -> new Exception("Usser does not exist"));	
		} catch (Exception e) {
			System.out.println(" Id apartado: "+apartado);
			System.out.println(" Id user: "+idUser);
			e.printStackTrace();
		}
		
		cc = checkoutRepository.findByApartadoAndUser(apa, uu);
		
		if (cc == null) {
			checkoutRepository.save(new Checkout(check, apa, uu));
		}else {
			cc.setChecking(check);
			checkoutRepository.save(cc);
		}
					
		
	}
	
	public Checkout getCheckoutByApartadoAndUser(Long apartado, Long idUser) {
		Apartado apa = new Apartado();
		User uu = new User();
		Checkout co = new Checkout();
		try {
			apa = apartadoRepository.findById(apartado)
					.orElseThrow(() -> new Exception("Apartado does not exist"));	
			uu = userRepository.findById(idUser)
					.orElseThrow(() -> new Exception("Usser does not exist"));	
		} catch (Exception e) {
			System.out.println(" Id apartado: "+apartado);
			System.out.println(" Id user: "+idUser);
			e.printStackTrace();
		}
		co = checkoutRepository.findByApartadoAndUser(apa, uu);
		System.out.println(" pag 165 "+co);
		
		return co;
	}
	
	


	
	public User getLoguin() {
		User userLogado = new User();

		
	    Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
	    System.out.println(userDetail.getUsername());
	    try {
	    	userLogado = this.usuarioService.getUserByName(userDetail.getUsername());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userLogado;
	}

}
