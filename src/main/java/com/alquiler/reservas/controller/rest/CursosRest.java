package com.alquiler.reservas.controller.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alquiler.reservas.dto.ApartadoDTO;
import com.alquiler.reservas.dto.CapituloDTO;
import com.alquiler.reservas.entity.Apartado;
import com.alquiler.reservas.entity.Apunte;
import com.alquiler.reservas.entity.Capitulo;
import com.alquiler.reservas.entity.CategoriaCurso;
import com.alquiler.reservas.entity.Curso;
import com.alquiler.reservas.entity.Respuesta;
import com.alquiler.reservas.entity.User;
import com.alquiler.reservas.repository.ApartadoRepository;
import com.alquiler.reservas.repository.CapituloRepository;
import com.alquiler.reservas.repository.CursoRepository;
import com.alquiler.reservas.service.CursoService;
import com.alquiler.reservas.service.UserService;
import com.alquiler.reservas.util.Excel;

@RestController
public class CursosRest {
	
	@Autowired
	CursoService cursoService;
	
	@Autowired
	UserService usuarioService;
	
	@Autowired
	CapituloRepository capituloRepository;
	
	@Autowired
	ApartadoRepository apartadoRepository;
	
	@Autowired
	CursoRepository cursoRepository;

	Excel utilExcel = new Excel();
	
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

	@GetMapping("/getApartado/{apartado}")
	public Apartado getApartado(@PathVariable(name="apartado") Long apartado) throws Exception {
		
		Apartado apar = apartadoRepository.findById(apartado)
				.orElseThrow(() -> new Exception("Apartado does not exist"));
		return new Apartado(apar.id,apar.nombre,apar.descripcion,apar.recurso,apar.orden);
	}	
	
	@GetMapping("/getApartadosByCap/{capitulo}")
	public List<ApartadoDTO> getApartadosByCap(
			@PathVariable(name="capitulo") Long capitulo
			) throws Exception {

		return cursoService.getApartadosByCap(capitulo);
	}
	@GetMapping("/getCapitulosByCurso/{curso}")
	public List<CapituloDTO> getCapitulosByCurso(
			@PathVariable(name="curso") int curso
			) throws Exception {

		return cursoService.getCapitulosByCurso(curso);
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
		Curso modCurso = 
				new Curso(id,formatearURL(nombre),
						CategoriaCurso.BACK, formatearURL(descripcion),
						formatearURL(fuente), formatearURL(urlimg)
						, formatearURL(urlIcono));
		return cursoService.modCurso(modCurso);
	}
	@GetMapping("altacurso/{nombre}/{descripcion}/{fuente}/{urlIcono}/{urlimg}")
	public Respuesta altaCurso(
			
			@ModelAttribute("nombre") String nombre,
			@ModelAttribute("descripcion") String descripcion,
			@ModelAttribute("fuente") String fuente,
			@ModelAttribute("urlIcono") String urlIcono,
			@ModelAttribute("urlimg") String urlimg
			
			) {		
		Curso modCurso = 
				new Curso(formatearURL(nombre)
						, formatearURL(descripcion),
						formatearURL(fuente), formatearURL(urlimg)
						, formatearURL(urlIcono));
		return cursoService.altaCurso(modCurso);
	}
	@GetMapping("editCapitulo/{id}/{nombre}/{descripcion}/{orden}")
	public Respuesta editarCapitulo(
			@ModelAttribute("id") Long id,
			@ModelAttribute("nombre") String nombre,
			@ModelAttribute("descripcion") String descripcion,
			@ModelAttribute("orden") int orden			
			) {
		
		return cursoService.modCapitulo(new Capitulo(id, nombre, descripcion, orden));
	}
	
	@GetMapping("editarApartado/{id}/{nombre}/{descripcion}/{recurso}/{orden}")
	public Respuesta editarApartado(
			@ModelAttribute("id") Long id,
			@ModelAttribute("nombre") String nombre,
			@ModelAttribute("descripcion") String descripcion,
			@ModelAttribute("recurso") String recurso,
			@ModelAttribute("orden") int orden			
			) {
		
		return cursoService.modApartado(new Apartado(id, nombre, descripcion,recurso, orden));
	}	
	@GetMapping("addCapitulo/{id}/{nombre}/{descripcion}/{orden}")
	public Respuesta addCapitulo(
			@ModelAttribute("id") int id,
			@ModelAttribute("nombre") String nombre,
			@ModelAttribute("descripcion") String descripcion,
			@ModelAttribute("orden") int orden			
			) {
		
		return new Respuesta(cursoService.addCapitulo(id, nombre, descripcion, orden));
	}
	@GetMapping("addApartado/{id}/{nombre}/{descripcion}/{recurso}/{orden}")
	public Respuesta addApartado(
			@ModelAttribute("id") Long id,
			@ModelAttribute("nombre") String nombre,
			@ModelAttribute("descripcion") String descripcion,
			@ModelAttribute("recurso") String recurso,
			@ModelAttribute("orden") int orden			
			) {
		
		return cursoService.addApartado(id, nombre, descripcion,recurso, orden);
	}
	@GetMapping("deleteCapitulo/{id}")
	public Respuesta deleteCapitulo(
			@ModelAttribute("id") Long id			
			) {
		return cursoService.deleteCapitulo(id);
	}
	@GetMapping("deleteApartado/{id}")
	public Respuesta deleteApartado(
			@ModelAttribute("id") Long id			
			) {
		
		return cursoService.deleteApartado(id);
	}
	
	@GetMapping("/importCurso/{json}")
	public Respuesta importCurso(Model model,
			@PathVariable(name="json") String json) {
		System.out.println("Get json"+json);
		try {
			json = utilExcel.quitarComillas(json);
			List<String> campos = utilExcel.getCampos(json);			
			
			return new Respuesta(true, String.valueOf(createCursoByStringsExcel(campos).getId()));
		}catch(Exception e) {
			e.printStackTrace();
			return new Respuesta(false, e.getMessage());
		}

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
			char c = url.charAt(n);
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

	 
    public Curso createCursoByStringsExcel(List<String> campos) {

        Curso curso = new Curso("","","","","");
        Capitulo capitulo = null;
        
        Apartado apartado = null;

        
            for (int i=0; i<campos.size(); i++) {
            	//System.out.println(" Get valor previo Switch: "+campos.get(i));
                switch (campos.get(i)) {
            	
            		case "Curso":
            			i=i+2;            			
            			curso.setNombre(campos.get(i));
            			i=i+2;
            			curso.setDescripcion(campos.get(i));
            			i=i+2;
            			curso.setFuente(campos.get(i));
            			i=i+2;
            			curso.setUrlimagen(campos.get(i));
            			i=i+2;
            			curso.setUrlicono(campos.get(i));
            			i++;
            			
            			cursoService.altaCurso(curso);
            			//cursoRepository.save(curso);
            			//System.out.println("Alta curso: "+curso.toString());
            		break;
            		case "Capitulo":
            			
            			capitulo = new Capitulo("","",-1);
            			i=i+2;
            			System.out.println(campos.get(i));
            			capitulo.setNombre(campos.get(i));
            			i=i+2;
            			System.out.println(campos.get(i));
            			capitulo.setDescripcion(campos.get(i));
            			i=i+2;
            			System.out.println(campos.get(i));
            			capitulo.setOrden(Integer.parseInt(campos.get(i)));
            			i++;
						capitulo.setCurso(curso);
						capituloRepository.save(capitulo);
						//System.out.println(" Capitulo >> "+capitulo.toString());           			
            			
            		break;
            		case "Apartado":
            			apartado = new Apartado("","","",-1);
            			i=i+2;
						apartado.setNombre(campos.get(i));
            			i=i+2;
						apartado.setDescripcion(campos.get(i));
            			i=i+2;
						apartado.setRecurso(campos.get(i));
            			i=i+2;
						apartado.setOrden(Integer.parseInt(campos.get(i)));
            			i++;
						apartado.setCapitulo(capitulo);
						apartadoRepository.save(apartado);
						//System.out.println(" Apartado >> "+apartado.toString());                 			
            			
            			
            		break;
            		default :
            			System.out.println(" << "+campos.get(i)+" >>");
            	
            	
                }	
            }
            //System.out.println(" Creado curso ");

            return curso;
    } 

    @GetMapping("/cursosPaginados")
    public String getCursos(){
    	System.out.println("/cursosPaginados");
    	final Pageable pageable = PageRequest.of(0, 10);
    	
    	System.out.println("/cursosPaginados");
    	Page<Curso> paginado =cursoService.findAll(pageable);
    	
    	System.out.println(paginado.getContent().size());
    	System.out.println("Paginado: "+paginado);
		return "/cursosPaginados";
    	
    }
	
}
