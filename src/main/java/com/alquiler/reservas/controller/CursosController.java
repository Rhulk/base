package com.alquiler.reservas.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.exceptions.ParserInitializationException;

import com.alquiler.reservas.entity.Apartado;
import com.alquiler.reservas.entity.Capitulo;
import com.alquiler.reservas.entity.Curso;
import com.alquiler.reservas.entity.Estado;
import com.alquiler.reservas.entity.Todo;
import com.alquiler.reservas.repository.ApartadoRepository;
import com.alquiler.reservas.repository.CapituloRepository;
import com.alquiler.reservas.repository.CursoRepository;
import com.alquiler.reservas.service.CursoService;
import com.alquiler.reservas.util.Excel;

@Controller
public class CursosController {

	@Autowired
	CursoService cursoService;

	@Autowired
	CursoRepository cursoRepository;

	@Autowired
	ApartadoRepository apartadoRepository;

	@Autowired
	CapituloRepository capituloRepository;

	@GetMapping("/cursoAlta")
	public String cursoAlta(Model model) {

		Curso curso = new Curso();

		// Gestión de la activación de los formularios
		model.addAttribute("activoFormTodo", false);
		model.addAttribute("activoFormUser", false);
		model.addAttribute("activoFormCurso", false);

		// Gestion del edit o del dashboard
		model.addAttribute("activoDashboardCurso", false);
		model.addAttribute("activoEditarCurso", true);

		// Gestión de tab acvitos o no
		model.addAttribute("tabTodo", "no");
		model.addAttribute("listTabTodo", "no");
		model.addAttribute("tabUser", "no");
		model.addAttribute("listTabUser", "no");
		model.addAttribute("detailTabCurso", "no");
		model.addAttribute("tabCurso", "active");
		model.addAttribute("listTabCurso", "no");
		model.addAttribute("misTabCurso", "no");
		model.addAttribute("editarTabCurso", "");
		model.addAttribute("editarOrAltaTabCurso", "active");
		model.addAttribute("altaTabCurso", "active");

		// Gestión de los btn alta o editar
		model.addAttribute("btn_alt", "");
		model.addAttribute("btn_gua", "oculto");
		model.addAttribute("btn_cap", "oculto");
		model.addAttribute("btn_import", "");

		// modelo de datos
		model.addAttribute("editarcurso", curso);

		// OLD
		/*
		 * // Gestión de la activación de los formularios
		 * model.addAttribute("activoFormTodo",false);
		 * model.addAttribute("activoFormUser",false);
		 * model.addAttribute("activoFormCurso", false); // Gestión de tab acvitos o no
		 * model.addAttribute("tabTodo","no"); model.addAttribute("listTabTodo","no");
		 * model.addAttribute("tabUser", "no"); model.addAttribute("listTabUser","no");
		 * model.addAttribute("detailTabCurso","no");
		 * model.addAttribute("tabCurso","active");
		 * model.addAttribute("listTabCurso","no");
		 * model.addAttribute("misTabCurso","no");
		 * model.addAttribute("editarTabCurso","no");
		 * model.addAttribute("altaTabCurso","active");
		 * 
		 * // modelo de datos
		 */

		return "security/user-form/main-view.html";
	}
	/*
	 * @PostMapping("/cursoAlta") public String
	 * createCurso(@Valid @ModelAttribute("detallecurso")Curso curso, BindingResult
	 * result, ModelMap model) { if(result.hasErrors()) {
	 * 
	 * model.addAttribute("detallecurso", curso);
	 * //model.addAttribute("tipos",genericos.getAllTipos());
	 * //model.addAttribute("prioridades", genericos.getAllPrioridades());
	 * //model.addAttribute("estados",genericos.getEstadosSelected());
	 * 
	 * // Gestión de tab acvitos o no model.addAttribute("tabTodo","no");
	 * model.addAttribute("listTabTodo","no"); model.addAttribute("tabUser", "no");
	 * model.addAttribute("listTabUser","no");
	 * model.addAttribute("detailTabCurso","no");
	 * model.addAttribute("tabCurso","active");
	 * model.addAttribute("listTabCurso","no");
	 * model.addAttribute("misTabCurso","no");
	 * model.addAttribute("editarTabCurso","no");
	 * model.addAttribute("altaTabCurso","active");
	 * 
	 * // Gestión de la activación de los formularios
	 * model.addAttribute("activoFormTodo",false);
	 * model.addAttribute("activoFormUser",false);
	 * model.addAttribute("activoFormCurso", false);
	 * 
	 * // TEST System.out.println("Error: "+result.getAllErrors());
	 * 
	 * }else { // TEST System.out.println("Save Curso");
	 * //todoService.createTodo(todo);
	 * 
	 * // gestion de la visualización en main-view
	 * //model.addAttribute("activoFormTodo",true);
	 * 
	 * // objetos modulos
	 * //model.addAttribute("toDoList",todoService.getByEstado(Estado.Activos));
	 * 
	 * // gestion visualización tab act model.addAttribute("tabTodo","no");
	 * model.addAttribute("listTabTodo","no"); model.addAttribute("tabUser", "no");
	 * model.addAttribute("listTabUser","no");
	 * model.addAttribute("detailTabCurso","no");
	 * model.addAttribute("tabCurso","active");
	 * model.addAttribute("listTabCurso","active");
	 * model.addAttribute("misTabCurso","no");
	 * model.addAttribute("editarTabCurso","no");
	 * model.addAttribute("altaTabCurso","no");
	 * 
	 * }
	 * 
	 * return "security/user-form/main-view"; }
	 */

	@GetMapping("/cursolistEdit/{id}")
	public String cursolistEdit(Model model, @PathVariable(name = "id") Integer id) throws Exception {

		Curso curso = cursoService.getCurso(id);

		// Gestión de la activación de los formularios
		model.addAttribute("activoFormTodo", false);
		model.addAttribute("activoFormUser", false);
		model.addAttribute("activoFormCurso", false);

		// Gestion del edit o del dashboard
		model.addAttribute("activoDashboardCurso", false);
		model.addAttribute("activoEditarCurso", true);

		// Gestión de tab acvitos o no
		model.addAttribute("tabTodo", "no");
		model.addAttribute("listTabTodo", "no");
		model.addAttribute("tabUser", "no");
		model.addAttribute("listTabUser", "no");
		model.addAttribute("detailTabCurso", "no");
		model.addAttribute("tabCurso", "active");
		model.addAttribute("listTabCurso", "no");
		model.addAttribute("misTabCurso", "no");
		model.addAttribute("editarTabCurso", "active");
		model.addAttribute("altaTabCurso", "no");
		model.addAttribute("editarOrAltaTabCurso", "active");

		// Gestión de los btn alta o editar
		model.addAttribute("btn_alt", "oculto");
		model.addAttribute("btn_gua", "");
		model.addAttribute("btn_import", "oculto");

		// modelo de datos
		model.addAttribute("editarcurso", curso);

		return "security/user-form/main-view.html";
	}

	@GetMapping("/cursolistEdit")
	public String cursolistEdit(Model model) {

		// Gestión de la activación de los formularios
		model.addAttribute("activoFormTodo", false);
		model.addAttribute("activoFormUser", false);
		model.addAttribute("activoFormCurso", false);

		// Gestion del edit o del dashboard
		model.addAttribute("activoDashboardCurso", true);
		model.addAttribute("activoEditarCurso", false);

		// Gestion de los btn editar o detalle
		model.addAttribute("detalleCurso", "oculto");
		model.addAttribute("editarCurso", "");

		// Gestión de tab acvitos o no
		model.addAttribute("tabTodo", "no");
		model.addAttribute("listTabTodo", "no");
		model.addAttribute("tabUser", "no");
		model.addAttribute("listTabUser", "no");
		model.addAttribute("detailTabCurso", "no");
		model.addAttribute("tabCurso", "active");
		model.addAttribute("listTabCurso", "no");
		model.addAttribute("misTabCurso", "no");
		model.addAttribute("editarTabCurso", "active");
		model.addAttribute("altaTabCurso", "no");
		model.addAttribute("editarOrAltaTabCurso", "active");

		// Gestión de los btn activos
		model.addAttribute("btn_cap", "");

		// modelo de datos
		model.addAttribute("cursos", cursoRepository.findAll());

		return "security/user-form/main-view.html";
	}

	@GetMapping("/cursolistPropios")
	public String cursolistPropios(Model model) {

		// Gestión de la activación de los formularios
		model.addAttribute("activoFormTodo", false);
		model.addAttribute("activoFormUser", false);
		model.addAttribute("activoFormCurso", false);

		// Gestion del edit o del dashboard
		model.addAttribute("activoDashboardCurso", false);
		model.addAttribute("activoEditarCurso", false);

		// Gestion de los btn editar o detalle
		model.addAttribute("detalleCurso", "");
		model.addAttribute("editarCurso", "oculto");

		// Gestión de tab acvitos o no
		model.addAttribute("tabTodo", "no");
		model.addAttribute("listTabTodo", "no");
		model.addAttribute("tabUser", "no");
		model.addAttribute("listTabUser", "no");
		model.addAttribute("detailTabCurso", "no");
		model.addAttribute("tabCurso", "active");
		model.addAttribute("listTabCurso", "no");
		model.addAttribute("misTabCurso", "active");
		model.addAttribute("editarTabCurso", "no");
		model.addAttribute("altaTabCurso", "no");

		// modelo de datos Pendiente solo recuperar los del usuario
		model.addAttribute("cursos", cursoService.getCursosByLoguinUser());

		// TEST

		return "security/user-form/main-view.html";
	}

	@GetMapping("/cursolist/{page}/{sizePage}")
	public String cursoList(Model model, @PageableDefault(size = 10) Pageable paginacion
			, @PathVariable(name = "page") Integer page
			, @PathVariable(name = "sizePage") Integer sizePage) {

		// Gestión de la activación de los formularios
		model.addAttribute("activoFormTodo", false);
		model.addAttribute("activoFormUser", false);
		model.addAttribute("activoFormCurso", false);

		// Gestion del edit o del dashboard
		model.addAttribute("activoDashboardCurso", false);
		model.addAttribute("activoEditarCurso", false);

		// Gestion de los btn editar o detalle
		model.addAttribute("detalleCurso", "");
		model.addAttribute("editarCurso", "oculto");

		// Gestión de tab acvitos o no
		model.addAttribute("tabTodo", "no");
		model.addAttribute("listTabTodo", "no");
		model.addAttribute("tabUser", "no");
		model.addAttribute("listTabUser", "no");
		model.addAttribute("detailTabCurso", "no");
		model.addAttribute("tabCurso", "active");
		model.addAttribute("listTabCurso", "active");
		model.addAttribute("misTabCurso", "no");
		model.addAttribute("editarTabCurso", "no");
		model.addAttribute("altaTabCurso", "no");
		


		// modelo de datos
		// cursoService.findAll(pageable)
		//List<Curso> cursos = (List<Curso>) cursoRepository.findAll();
		final Pageable pageable = PageRequest.of(page, sizePage);
		List<Curso> cursos = cursoService.findAll(pageable).getContent();
		Page<Curso> pag = cursoService.findAll(pageable);

		// Gestión de la paginación
		model.addAttribute("back", pageable.getPageNumber() -1 );
		model.addAttribute("firtPage", 0);
		model.addAttribute("cursos", pag.getContent());
		model.addAttribute("lastPage", pag.getTotalPages()-1);
		model.addAttribute("sizePage", 9);		
		model.addAttribute("nCursos",  pag.getTotalElements());
		model.addAttribute("pageSize", pag.getTotalPages());	
		
		if (pag.isLast()) {
			model.addAttribute("pageNumber", pageable.getPageNumber() );	
			model.addAttribute("isLast", true);
		
		} else {
			model.addAttribute("pageNumber", pageable.getPageNumber()+1 );
			model.addAttribute("isLast", false);
			
		}
		if (pag.isFirst()) {
			model.addAttribute("isFirst", true);
		}else {
			model.addAttribute("isFirst", false);
		}
		

		

		
		
		// TEST

		return "security/user-form/main-view.html";
	}

	@GetMapping("/curso{id}")
	public String cursoDetalleID(Model model, @PathVariable(name = "id") Integer id) throws Exception {

		Curso curso = cursoService.getCurso(id);

		// Gestión de la activación de los formularios
		model.addAttribute("activoFormTodo", false);
		model.addAttribute("activoFormUser", false);
		model.addAttribute("activoFormCurso", true);

		// Gestión de tab acvitos o no
		model.addAttribute("tabTodo", "no");
		model.addAttribute("listTabTodo", "no");
		model.addAttribute("tabUser", "no");
		model.addAttribute("listTabUser", "no");
		model.addAttribute("tabCurso", "active");
		model.addAttribute("listTabCurso", "no");
		model.addAttribute("detailTabCurso", "active");

		// Gestión de los objetos que se mandan a la vista.
		model.addAttribute("detallecurso", curso);
		model.addAttribute("capitulos", curso.getCapitulos());
		model.addAttribute("nCap", curso.getCapitulos().size());
		model.addAttribute("apartados", cursoService.getApartados(curso));
		model.addAttribute("nApartados", cursoService.getApartados(curso).size());

		// TEST
		System.out.println("capitulos : " + cursoService.getCurso(id).getCapitulos());
		System.out.println("apartados : " + cursoService.getApartados(curso));

		System.out.println("nCapitulos : " + cursoService.getCurso(id).getCapitulos().size());
		System.out.println("nCapitulos2 : " + cursoService.getCapitulos(cursoService.getCurso(id)).size());

		return "security/user-form/main-view.html";
	}

	@GetMapping("/importCurso")
	public String importCurso(Model model) {

		Curso curso = new Curso();

		// Gestión de la activación de los formularios
		model.addAttribute("activoFormTodo", false);
		model.addAttribute("activoFormUser", false);
		model.addAttribute("activoFormCurso", false);

		// Gestion del edit o del dashboard
		model.addAttribute("activoDashboardCurso", false);
		model.addAttribute("activoEditarCurso", true);

		// Gestión de tab acvitos o no
		model.addAttribute("tabTodo", "no");
		model.addAttribute("listTabTodo", "no");
		model.addAttribute("tabUser", "no");
		model.addAttribute("listTabUser", "no");
		model.addAttribute("detailTabCurso", "no");
		model.addAttribute("tabCurso", "active");
		model.addAttribute("listTabCurso", "no");
		model.addAttribute("misTabCurso", "no");
		model.addAttribute("editarTabCurso", "");
		model.addAttribute("editarOrAltaTabCurso", "active");
		model.addAttribute("altaTabCurso", "active");

		// Gestión de los btn alta o editar
		model.addAttribute("btn_alt", "");
		model.addAttribute("btn_gua", "oculto");
		model.addAttribute("btn_cap", "oculto");

		// Test Cargando excel

		Excel excel = new Excel();

		lecturaExcel();// Recuperar el curso

		// modelo de datos
		model.addAttribute("editarcurso", curso);

		return "security/user-form/main-view.html";
	}

	/*
	 * @GetMapping("/importCurso/{json}") public String importCurso(Model model,
	 * 
	 * @PathVariable(name="json") String json) {
	 * 
	 * //System.out.println(" TEST "+json); json = quitarComillas(json);
	 * List<String> campos = getCampos(json);
	 * 
	 * Curso curso = createCursoByStringsExcel(campos);
	 * 
	 * 
	 * // Gestión de la activación de los formularios
	 * model.addAttribute("activoFormTodo",false);
	 * model.addAttribute("activoFormUser",false);
	 * model.addAttribute("activoFormCurso", false);
	 * 
	 * // Gestion del edit o del dashboard
	 * model.addAttribute("activoDashboardCurso", false);
	 * model.addAttribute("activoEditarCurso", true);
	 * 
	 * // Gestión de tab acvitos o no model.addAttribute("tabTodo","no");
	 * model.addAttribute("listTabTodo","no"); model.addAttribute("tabUser", "no");
	 * model.addAttribute("listTabUser","no");
	 * model.addAttribute("detailTabCurso","no");
	 * model.addAttribute("tabCurso","active");
	 * model.addAttribute("listTabCurso","no");
	 * model.addAttribute("misTabCurso","no");
	 * model.addAttribute("editarTabCurso","active");
	 * model.addAttribute("editarOrAltaTabCurso","active");
	 * model.addAttribute("altaTabCurso","");
	 * 
	 * // Gestión de los btn alta o editar model.addAttribute("btn_alt", "oculto");
	 * model.addAttribute("btn_gua", ""); model.addAttribute("btn_cap","oculto");
	 * 
	 * 
	 * // Test Cargando excel
	 * 
	 * 
	 * // modelo de datos model.addAttribute("editarcurso",curso );
	 * 
	 * 
	 * // Test
	 * 
	 * // Gestión de la activación de los formularios
	 * model.addAttribute("activoFormTodo",false);
	 * model.addAttribute("activoFormUser",false);
	 * model.addAttribute("activoFormCurso", false);
	 * 
	 * // Gestion del edit o del dashboard
	 * model.addAttribute("activoDashboardCurso", false);
	 * model.addAttribute("activoEditarCurso", true);
	 * 
	 * // Gestión de tab acvitos o no model.addAttribute("tabTodo","no");
	 * model.addAttribute("listTabTodo","no"); model.addAttribute("tabUser", "no");
	 * model.addAttribute("listTabUser","no");
	 * model.addAttribute("detailTabCurso","no");
	 * model.addAttribute("tabCurso","active");
	 * model.addAttribute("listTabCurso","no");
	 * model.addAttribute("misTabCurso","no");
	 * model.addAttribute("editarTabCurso","active");
	 * model.addAttribute("altaTabCurso","no");
	 * model.addAttribute("editarOrAltaTabCurso","active");
	 * 
	 * // Gestión de los btn alta o editar model.addAttribute("btn_alt", "oculto");
	 * model.addAttribute("btn_gua", "");
	 * 
	 * 
	 * // modelo de datos model.addAttribute("editarcurso",curso );
	 * 
	 * System.out.println("Curso: "+curso.toString()); return
	 * "security/user-form/main-view.html"; }
	 * 
	 */

	public void lecturaExcel() {

		String excelFilePath = "C:\\Users\\kike\\Documents\\ZK_oficina\\Tracking\\src\\main\\resources\\static\\doc\\Lectura.xls";
		String tipo = "";
		String x2 = "";
		Random rd = new Random();
		Long idCap = new Long(0);
		Long idApar = new Long(0);

		Curso curso = new Curso("", "", "", "", "");
		Capitulo capitulo = new Capitulo("", "", -1);
		Apartado apartado = new Apartado("", "", "", -1);
		try {
			FileInputStream file = new FileInputStream(new File(excelFilePath));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet ws = wb.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = ws.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					// Check the cell type and format accordingly
					switch (cell.getCellType()) {

					case NUMERIC:
					case STRING:
						int num = cell.getCellType().getCode();// 0 is double 1 String

						if (cell.getCellType().getCode() == 1) {
							x2 = cell.getStringCellValue();
						} else {
							x2 = "" + cell.getNumericCellValue();
						}

						// System.out.println("x2:"+x2);
						if (x2.equals("Curso")) {
							tipo = x2;
						}
						if (x2.equals("Capitulo")) {
							tipo = x2;
							// idCap = idCap + new Long(1);
							capitulo = new Capitulo("", "", -1);
						}
						if (x2.equals("Apartado")) {
							tipo = x2;
							// idApar = idApar + new Long(1);
							apartado = new Apartado("", "", "", -1);

						}
						if (x2.equals("Leyenda")) {
							tipo = x2;
						}
						// System.out.println(" Tipo:"+tipo);
						switch (tipo) {

						case "Curso":

							if (!x2.equals("Curso")) {
								if (curso.getNombre().isBlank()) {
									curso.setNombre(x2);
								} else {
									if (curso.getDescripcion().isEmpty()) {
										curso.setDescripcion(x2);
									} else {
										if (curso.getFuente().isEmpty()) {
											curso.setFuente(x2);
										} else {
											if (curso.getUrlimagen().isEmpty()) {
												curso.setUrlimagen(x2);
											} else {
												if (curso.getUrlicono().isEmpty()) {
													curso.setUrlimagen(x2);
												}
												if (curso.getId() == 0) {// save curso
													cursoRepository.save(curso);
													// cursoService.altaCurso(curso);
													System.out.println("Curso >> " + curso.toString());
												}

											}
										}
									}
								}

							}
							break;
						case "Capitulo":

							if (!isCabeceraCapitulo(x2)) {
								if (setNameWhenIsBlank(capitulo, x2)) {// si ya hay nombre pasa al siguiente
									if (setDescripcionWhenIsBlank(capitulo, x2)) { // setDescripciónWhenIsBlank
										if (setOrdenWhenIsBlank(capitulo, x2)) {

										}
										capitulo.setCurso(curso);
										capituloRepository.save(capitulo);
										System.out.println(" Capitulo >> " + capitulo.toString());
									}
								}

							}
							break;
						case "Apartado":

							if (!isCabeceraApartado(x2)) {
								if (setNombreWhenIsBlank(apartado, x2)) {// si ya hay nombre pasa al siguiente
									if (setDescripcionWhenIsBlank(apartado, x2)) { // setRecursoWhenIsBlank
										if (setRecursoWhenIsBlank(apartado, x2)) {
											if (setOrdenWhenIsBlank(apartado, x2)) {

											}
											apartado.setCapitulo(capitulo);
											apartadoRepository.save(apartado);
											System.out.println(" Apartado >> " + apartado.toString());
										}
									}
								}

							}

							break;
						case "Leyenda":
							// System.out.println(" Leyenda ");
							break;
						default:
							// System.out.println(" << "+x2+" >>");

						}

						break;

					}
				}
			}
			file.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public boolean isCabeceraCapitulo(String campo) {
		if (campo.equals("Capitulo")) {
			return true;
		}
		return false;
	}

	/*
	 * Return false when is not blank
	 */
	public boolean setNameWhenIsBlank(Capitulo capitulo, String nombre) {
		if (capitulo.getNombre().isBlank()) {
			capitulo.setNombre(nombre);
			return false;
		}
		return true;
	}

	public boolean setDescripcionWhenIsBlank(Capitulo capitulo, String descripcion) {

		if (capitulo.getDescripcion().isBlank()) {
			capitulo.setDescripcion(descripcion);
			return false;
		}

		return true;
	}

	public boolean setOrdenWhenIsBlank(Capitulo capitulo, String orden) {

		if (capitulo.getOrden() == -1) {
			capitulo.setOrden(Integer.valueOf(orden.substring(0, 1)));
			return false;
		}

		return true;
	}

	public boolean isCabeceraApartado(String campo) {

		if (campo.equals("Apartado")) {
			return true;
		}

		return false;
	}

	public boolean setNombreWhenIsBlank(Apartado apartado, String nombre) {

		if (apartado.getNombre().isBlank()) {
			apartado.setNombre(nombre);
			return false;
		}
		return true;
	}

	public boolean setDescripcionWhenIsBlank(Apartado apartado, String descripcion) {

		if (apartado.getDescripcion().isBlank()) {
			apartado.setDescripcion(descripcion);
			return false;
		}

		return true;
	}

	public boolean setRecursoWhenIsBlank(Apartado apartado, String recurso) {

		if (apartado.getRecurso().isBlank()) {
			apartado.setRecurso(recurso);
			return false;
		}

		return true;
	}

	public boolean setOrdenWhenIsBlank(Apartado apartado, String orden) {

		if (apartado.getOrden() == -1) {
			apartado.setOrden(Integer.valueOf(orden.substring(0, 1)));
			return false;
		}

		return true;
	}

}
