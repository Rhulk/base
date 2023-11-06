package com.alquiler.reservas.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import com.alquiler.reservas.entity.Apunte;
import com.alquiler.reservas.entity.Capitulo;
import com.alquiler.reservas.entity.CategoriaCurso;
import com.alquiler.reservas.entity.Curso;
import com.alquiler.reservas.entity.Respuesta;
import com.alquiler.reservas.entity.User;
import com.alquiler.reservas.repository.CapituloRepository;
import com.alquiler.reservas.service.CursoService;
import com.alquiler.reservas.service.UserService;

@RestController
public class CursosRest {
	
	@Autowired
	CursoService cursoService;
	
	@Autowired
	UserService usuarioService;
	
	@Autowired
	CapituloRepository capituloRepository;
	
	@GetMapping("/apuntes/{apartado}/{pag}/{curso}")
	public List <Apunte> getApunteByApartado(
			@PathVariable(name="apartado") Long apartado,
			@PathVariable(name="pag") int pag,
			@PathVariable(name="curso") Integer curso
			){
		
		return cursoService.getApunte(apartado,pag,curso);
	}
	
	@GetMapping("/apuntes/{apartado}/{curso}")
	public int getCantidadAportesByApartado(
			@PathVariable(name="apartado") Long apartado,
			@PathVariable(name="curso") Integer curso			
			) {
		
		return cursoService.getCantidadAportesByApartadoAndCurso(apartado, curso);
	}

	@GetMapping("/deleteapunte/{apunte}/{curso}")
	public Respuesta deleteAporte(
			@PathVariable(name="apunte") Long apunte,
			@PathVariable(name="curso") Integer curso
			) {
		
		return new Respuesta( cursoService.deleteApunte(cursoService.getApunteByIdAndCurso(apunte,curso)) );
	}
	
	@GetMapping("/saveAporteIn/{apartado}/{notas}/{curso}")
	public Respuesta saveAporteIn(
			@PathVariable(name="apartado") Long apartado,
			@PathVariable(name="notas") String notas,
			@PathVariable(name="curso") Integer curso
			){

		return new Respuesta( 
				cursoService.createNewAporte(
						apartado,notas,getLoguin().getId(),curso) 
				);
	}
	
	@GetMapping("/modApunteIn/{apunte}/{notas}/{curso}")
	public Respuesta modApunteIn(
			@PathVariable(name="apunte") Long apunte, 
			@PathVariable(name="notas") String notas,
			@PathVariable(name="curso") Integer curso
			) {

		return new Respuesta(cursoService.modApunte(apunte,notas,curso));
	}
	
	@GetMapping("/checkout/{apartado}/{check}/{curso}")
	public Respuesta checkOut(
			@PathVariable(name="apartado") Long apartado, 
			@PathVariable(name="check") boolean check,
			@PathVariable(name="curso") Integer curso
			) {
		
		return new Respuesta( cursoService.checking(apartado,check,getLoguin().getId(),curso) );
	}
	
	@GetMapping("/checkstatus/{apartado}/{curso}")
	public Respuesta checkStatus(
			@PathVariable(name="apartado") Long apartado,
			@PathVariable(name="curso") Integer curso
			) {
		
		return new Respuesta( cursoService.getCheckoutByApartadoAndUserAndCurso(apartado, getLoguin().getId(),curso).isChecking() );
	}
	
	@GetMapping("/follow/{curso}")
	public Respuesta follow(@PathVariable(name="curso") Integer curso) {
		
		return new Respuesta(cursoService.followCurso(curso));
	}
	
	@GetMapping("/unfollow/{curso}")
	public Respuesta unfollow(@PathVariable(name="curso") Integer curso) {
		
		return new Respuesta(cursoService.unfollowCurso(curso));
	}
	
	@GetMapping("/isfollow/{curso}")
	public Respuesta isfollow(@PathVariable(name="curso") Integer curso) {
		
		return new Respuesta(cursoService.isFollowCurso(curso));
	}
	
	@GetMapping("/getCapitulo/{capitulo}")
	public Capitulo getCapitulo(@PathVariable(name="capitulo") Long capitulo) throws Exception {
		
		Capitulo cap = capituloRepository.findById(capitulo)
				.orElseThrow(() -> new Exception("Capitulos does not exist"));
		
		return new Capitulo(cap.id,cap.nombre,cap.descripcion,cap.orden);
	}
	
	@GetMapping("editcurso/{id}/{nombre}/{descripcion}/{fuente}/{urlIcono}/{urlimg}")
	public Respuesta editCurso(
			@ModelAttribute("id") int id,
			@ModelAttribute("nombre") String nombre,
			@ModelAttribute("descripcion") String descripcion,
			@ModelAttribute("fuente") String fuente,
			
			@ModelAttribute("urlIcono") String urlIcono,
			@ModelAttribute("urlimg") String urlimg
			
			) {
		
		System.out.println(urlimg+" GET: urlImgen: "+urlimg+" | urlIcono: "+urlIcono);
		System.out.println(urlimg+" GET: urlImgen: "+formatearURL(urlimg)+" | urlIcono: "+formatearURL(urlIcono));
		Curso modCurso = 
				new Curso(id,formatearURL(nombre),
						CategoriaCurso.BACK, formatearURL(descripcion),
						formatearURL(fuente), formatearURL(urlimg)
						, formatearURL(urlIcono));
		return new Respuesta(cursoService.modCurso(modCurso));
	}
	@GetMapping("editCapitulo/{id}/{nombre}/{descripcion}/{orden}")
	public Respuesta editarCapitulo(
			@ModelAttribute("id") Long id,
			@ModelAttribute("nombre") String nombre,
			@ModelAttribute("descripcion") String descripcion,
			@ModelAttribute("orden") int orden			
			) {
		System.out.println(" GET REST Editar Capitulo: id : "+id+
				" | nombre: "+nombre+" descripcion: "+descripcion+" orden: "+orden);
		
		return new Respuesta(cursoService.modCapitulo(new Capitulo(id, nombre, descripcion, orden)));
	}
	
	public User getLoguin() {
		User userLogado = new User();
		
	    Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
	    try {
	    	userLogado = this.usuarioService.getUserByName(userDetail.getUsername());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userLogado;
	}
	
	/*
	 * 	Recuperar los caracteres originales de / que no puedo mandar por la petición REST
	 * 
	 */
	public String formatearURL(String url) {

		StringBuilder sb = new StringBuilder(url);
		for (int n = 0; n <url.length (); n++) {
			char c = url.charAt (n);
			if (c == 65533) {
				System.out.println("Caracter desconocido( "+c+" ) por /");

				sb.setCharAt(n, '/');
			}
			if (c == '$') {
				System.out.println("Dollar ( "+c+" ) por ?");
				System.out.println(c);
				sb.setCharAt(n, '?');
			}
		}
		System.out.println( sb.toString() );
		return sb.toString();
	}
	
	
}
