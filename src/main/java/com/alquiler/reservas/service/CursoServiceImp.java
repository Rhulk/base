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
import com.alquiler.reservas.entity.CursoUser;
import com.alquiler.reservas.entity.User;
import com.alquiler.reservas.repository.ApartadoRepository;
import com.alquiler.reservas.repository.ApunteRepository;
import com.alquiler.reservas.repository.CheckoutRepository;
import com.alquiler.reservas.repository.CursoRepository;
import com.alquiler.reservas.repository.CursoUserRepository;
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
	
	@Autowired
	CursoUserRepository cursoUserRepository;

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
	public List <Apunte> getApunte(Long apartado, int pag, Long curso) {
		
		System.out.println(cursoDAO.getApunteDAO(apartado,pag,curso));
		
		return cursoDAO.getApunteDAO(apartado,pag,curso);
	}
	
	public int getCantidadAportesByApartadoAndCurso(Long apartado, Long curso) {
		
		return cursoDAO.getCantidadAportesByApartadoAndCursoDAO(apartado,curso);
	}

	public Apunte getApunteByIdAndCurso(Long apunte, Long idCurso) {
		Apunte apu = new Apunte();
		Curso cur = new Curso();
		try {
			// faild, I can not use a type of data Long
			// I have use the type Curso
			cur = cursoRepository.findById(idCurso).orElseThrow();
			apu = apunteRepository.findByIdAndCurso(apunte,cur);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return apu;
	}
	
	public boolean deleteApunte(Apunte apu) {
		
		try {
			apunteRepository.delete(apu);
			return true;
		}catch (Exception e) {
			e.getMessage();
			return false;
		}
	}

	@Override
	public boolean createNewAporte(Long apartado, String notas, Long idUser, Long idCurso) {
		Apartado apa = new Apartado();
		Curso curso = new Curso();
		try {
			apa = apartadoRepository.findById(apartado)
					.orElseThrow(() -> new Exception("Apartado does not exist"));
			curso = cursoRepository.findById(idCurso)
					.orElseThrow(() -> new Exception("Curso does not exist"));
			apunteRepository.save(new Apunte(notas,apa,idUser,curso));
			return true;
		} catch (Exception e) {
			System.out.println(" Id apartado: "+apartado);
			System.out.println(" Id user: "+idUser);
			System.out.println(" Id curso: "+idCurso);
			e.printStackTrace();
			return false;
		}
		
		
		
		
	}
	
	@Override
	public boolean modApunte(Long apunte, String notas, Long curso) {
		
		try {
			Apunte apu = getApunteByIdAndCurso(apunte,curso);
			apu.setNotas(notas);
			apunteRepository.save(apu);	
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	

	@Override
	public boolean checking(Long apartado, boolean check, Long idUser, Long idCurso) {
		Apartado apa = new Apartado();
		User uu = new User();
		Checkout cc = new Checkout();
		Curso curso = new Curso();
		
		try {
			apa = apartadoRepository.findById(apartado)
					.orElseThrow(() -> new Exception("Apartado does not exist"));	
			uu = userRepository.findById(idUser)
					.orElseThrow(() -> new Exception("Usser does not exist"));
			curso = cursoRepository.findById(idCurso)
					.orElseThrow(() -> new Exception("Curso does not exist"));
			
			cc = checkoutRepository.findByApartadoAndUserAndCurso(apa, uu,curso);
			
			if (cc == null) {
				checkoutRepository.save(new Checkout(check, apa, uu,curso));
			}else {
				cc.setChecking(check);
				checkoutRepository.save(cc);
			}		
			return true;
			
		} catch (Exception e) {
			System.out.println(" Id apartado: "+apartado);
			System.out.println(" Id user: "+idUser);
			e.printStackTrace();
			return false;
		}
		

					
		
	}
	
	public Checkout getCheckoutByApartadoAndUserAndCurso(Long apartado, Long idUser, Long curso) {
		Apartado apa = new Apartado();
		User uu = new User();
		Checkout co = new Checkout();
		Curso cur = new Curso();
		try {
			apa = apartadoRepository.findById(apartado)
					.orElseThrow(() -> new Exception("Apartado does not exist"));	
			uu = userRepository.findById(idUser)
					.orElseThrow(() -> new Exception("Usser does not exist"));	
			cur = cursoRepository.findById(curso)
					.orElseThrow(() -> new Exception("Curso does not exist"));	
		} catch (Exception e) {
			System.out.println(" Id apartado: "+apartado);
			System.out.println(" Id user: "+idUser);
			e.printStackTrace();
		}
		try {
			co = checkoutRepository.findByApartadoAndUserAndCurso(apa, uu, cur);
			if (co == null) {
				checkoutRepository.save(new Checkout(false, apa, uu,cur));
				co = new Checkout(false, apa, uu,cur);
			}			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(" Checkout: "+co);
		}

		System.out.println(" Checkout: "+co);
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

	public boolean followCurso(Long curso) {
		User uu = getLoguin();
		Curso cc = new Curso();
		
		try {
			cc = cursoRepository.findById(curso)
					.orElseThrow(() -> new Exception("Curso does not exist"));
		} catch (Exception e) {
			System.out.println("Faild");
			return false;
		}
		if (cursoUserRepository.findByCursoAndUser(cc, uu) == null) {
			System.out.println("Is null");
			cursoUserRepository.save(new CursoUser(cc,uu));
			return true;
		}
		System.out.println("Is not null");
		return true;
	}
	
	public boolean unfollowCurso(Long curso) {
		User uu = getLoguin();
		Curso cc = new Curso();
		CursoUser cu = new CursoUser();
		
		try {
			cc = cursoRepository.findById(curso)
					.orElseThrow(() -> new Exception("Curso does not exist"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		cu = cursoUserRepository.findByCursoAndUser(cc, uu);
		if (cu != null) {	
			cursoUserRepository.delete(cu);
			return true;			
		}

		
		return false;
	}

	@Override
	public boolean isFollowCurso(Long curso) {	
		User uu = getLoguin();
		Curso cc = new Curso();
		CursoUser cu = new CursoUser();
		
		try {
			cc = cursoRepository.findById(curso)
					.orElseThrow(() -> new Exception("Curso does not exist"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		cu = cursoUserRepository.findByCursoAndUser(cc, uu);
		if (cu != null) {	
			return true;			
		}

		
		return false;
	}





	
}
