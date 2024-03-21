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

import com.alquiler.reservas.dto.ApartadoDTO;
import com.alquiler.reservas.dto.CapituloDTO;
import com.alquiler.reservas.entity.Apartado;
import com.alquiler.reservas.entity.Apunte;
import com.alquiler.reservas.entity.CamposAndTipos;
import com.alquiler.reservas.entity.Capitulo;
import com.alquiler.reservas.entity.Checkout;
import com.alquiler.reservas.entity.Curso;
import com.alquiler.reservas.entity.CursoUser;
import com.alquiler.reservas.entity.Respuesta;
import com.alquiler.reservas.entity.User;
import com.alquiler.reservas.repository.ApartadoRepository;
import com.alquiler.reservas.repository.ApunteRepository;
import com.alquiler.reservas.repository.CapituloRepository;
import com.alquiler.reservas.repository.CheckoutRepository;
import com.alquiler.reservas.repository.CursoRepository;
import com.alquiler.reservas.repository.CursoUserRepository;
import com.alquiler.reservas.repository.UserRepository;

@Service
public class CursoServiceImp implements CursoService {

	@Autowired
	CursoRepository cursoRepository;
	
	@Autowired
	CapituloRepository capituloRepository;
	
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
	public Curso getCurso(int id) throws Exception {
		return cursoRepository.findById(id)
				.orElseThrow(() -> new Exception("Curso does not exist"));
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
	public List <Apunte> getApunte(Long apartado, int pag, Integer curso) {
		
		return cursoDAO.getApunteDAO(apartado,pag,curso);
	}
	
	public int getCantidadAportesByApartadoAndCurso(Long apartado, Integer curso) {
		
		return cursoDAO.getCantidadAportesByApartadoAndCursoDAO(apartado,curso);
	}

	public Apunte getApunteByIdAndCurso(Long apunte, Integer idCurso) {
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
	public boolean createNewAporte(Long apartado, String notas, Long idUser, Integer idCurso) {
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
	public boolean modApunte(Long apunte, String notas, Integer curso) {
		
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
	public boolean modCurso(Curso curso) {
		Curso cursoNew = new Curso();
		try {
			cursoNew = cursoRepository.findById(curso.id)
					.orElseThrow( () -> new Exception(" Fail found curso"));
			System.out.println(cursoNew);
			cursoNew.nombre = curso.nombre;
			cursoNew.descripcion = curso.descripcion;
			//cursoNew.categoriacurso = curso.categoriacurso; Falta implementarlo en el formulario
			cursoNew.fuente = curso.fuente;
			cursoNew.urlicono = curso.urlicono;
			cursoNew.urlimagen = curso.urlimagen;
			System.out.println(cursoNew);
			cursoRepository.save(
					cursoNew
			);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}	
	
	@Override
	public Respuesta modCapitulo(Capitulo capitulo) {
		Capitulo capiNew = new Capitulo();
		
		try {
			capiNew = capituloRepository.findById(capitulo.id)
					.orElseThrow( () -> new Exception(" Capitulo does not exist !!")  );
			capiNew.nombre = capitulo.nombre;
			capiNew.descripcion = capitulo.descripcion;
			capiNew.orden = capitulo.orden;
			
			capituloRepository.save(capiNew);
			
		}catch (Exception e) {
			e.printStackTrace();
			return new Respuesta(false, e.getMessage());
		}
		return new Respuesta(true,"Capitulo modificado");
	}
	
	@Override
	public Respuesta modApartado(Apartado apartado) {
		Apartado modApartado = new Apartado();
		
		try {
			modApartado = apartadoRepository.findById(apartado.id)
					.orElseThrow( () -> new Exception(" Apartado does not exist !!")  );

			modApartado.nombre = apartado.nombre;
			modApartado.descripcion = apartado.descripcion;
			modApartado.orden = apartado.orden;
			modApartado.recurso = apartado.recurso;

			apartadoRepository.save(modApartado);
			
		}catch (Exception e) {
			e.printStackTrace();
			return new Respuesta(false, e.getMessage());
		}
		return new Respuesta(true,"Apartado modificado");
	}
	
	public boolean addCapitulo(int curso, String nombre, String descripcion, int orden) {
		Curso findCurso = new Curso();
		Capitulo newCap = new Capitulo();
		try {
			findCurso = cursoRepository.findById(curso)
					.orElseThrow( () -> new Exception(" Fail found curso"));	
			newCap.nombre = nombre;
			newCap.descripcion = descripcion;
			newCap.orden = orden;
			newCap.setCurso(findCurso);
			
			capituloRepository.save(newCap);
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public Respuesta addApartado(Long capitulo, String nombre, String descripcion, String recurso, Integer orden) {
		
		Capitulo findCapi = new Capitulo();
		Apartado newApartado = new Apartado();
		try {
			findCapi = capituloRepository.findById(capitulo)
					.orElseThrow( () -> new Exception(" Fail found Capitulo"));	
			newApartado.nombre = nombre;
			newApartado.descripcion = descripcion;
			newApartado.orden = orden;
			newApartado.recurso = recurso;
			newApartado.setCapitulo(findCapi);
			
			apartadoRepository.save(newApartado);
			
		}catch (Exception e) {
			e.printStackTrace();
			return new Respuesta(false,e.getMessage());
		}
		
		return new Respuesta(true,"OK");
	}
	

	@Override
	public boolean checking(Long apartado, boolean check, Long idUser, Integer idCurso) {
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
	
	public Checkout getCheckoutByApartadoAndUserAndCurso(Long apartado, Long idUser, Integer curso) {
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

	public boolean followCurso(Integer curso) {
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
	
	public boolean unfollowCurso(Integer curso) {
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
	public boolean isFollowCurso(Integer curso) {	
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

	@Override
	public List<Curso> getCursosByLoguinUser() {
		List<Curso> ids = cursoDAO.findCursosByUser(getLoguin());
		List<Curso> cursos = new LinkedList<>();
		for(int i=0; i<ids.size();i++) {
			try {
				System.out.println(ids.get(i));

				cursos.add( getCurso( ids.get(i).getId() ) );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return cursos;
	}

	@Override
	public Respuesta deleteCapitulo(Long id) {
		Capitulo capDelete = new Capitulo();
		try {
			capDelete = capituloRepository.findById(id)
					.orElseThrow(() -> new Exception("Capitulo does not exist"));
			capituloRepository.delete(capDelete);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new Respuesta(false,e.getMessage());
		}	
		
		return new Respuesta(true, "!Delete capitulo!");
	}
	@Override
	public Respuesta deleteApartado(Long id) {
		Apartado aparDelete = new Apartado();
		try {
			aparDelete = apartadoRepository.findById(id)
					.orElseThrow(() -> new Exception("Apartado does not exist"));
			apartadoRepository.delete(aparDelete);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new Respuesta(false,e.getMessage());
		}	
		return new Respuesta(true, "!Delete apartado!");
	}

	@Override
	public List<ApartadoDTO> getApartadosByCap(Long capitulo) {
		
		try {
			Capitulo cap = capituloRepository.findById(capitulo)
					.orElseThrow(() -> new Exception("Capitulo does not exist"));
 
			return entityToDTO(apartadoRepository.findByCapitulo(cap));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<ApartadoDTO> entityToDTO(List<Apartado> apartados){
		List<ApartadoDTO> dtos = new LinkedList<>();
		
		for(Apartado apartado : apartados){
			ApartadoDTO tempDto = new ApartadoDTO();
			tempDto.setDescripcion(apartado.getDescripcion());
			tempDto.setId(apartado.getId());
			tempDto.setNombre(apartado.getNombre());
			tempDto.setOrden(apartado.getOrden());
			dtos.add(tempDto);
		}
		
		return dtos;
	}

	@Override
	public List<CapituloDTO> getCapitulosByCurso(int curso) {
		Curso findCurso = new Curso();
		try {
			findCurso = cursoRepository.findById(curso)
					.orElseThrow(() -> new Exception("Curso does not exist"));
			
			return entityToCapituloDTO(capituloRepository.findByCurso(findCurso));
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<CapituloDTO> entityToCapituloDTO(List<Capitulo> capitulos){
		List<CapituloDTO> dtos = new LinkedList<>();
		
		for (Capitulo capitulo : capitulos) {
			CapituloDTO tempDto = new CapituloDTO();
			tempDto.setDescripcion(capitulo.getDescripcion());
			tempDto.setId(capitulo.getId());
			tempDto.setNombre(capitulo.getNombre());
			tempDto.setOrden(capitulo.getOrden());
			dtos.add(tempDto);
		}
		return dtos;
	}



	
	
}
