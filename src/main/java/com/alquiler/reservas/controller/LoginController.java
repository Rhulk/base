package com.alquiler.reservas.controller;



import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

import com.alquiler.reservas.Exception.CustomeFieldValidationException;
import com.alquiler.reservas.entity.ChangePasswordForm;
import com.alquiler.reservas.entity.Role;
import com.alquiler.reservas.entity.Todo;
import com.alquiler.reservas.entity.User;
import com.alquiler.reservas.repository.RoleRepository;
import com.alquiler.reservas.repository.UserRepository;
import com.alquiler.reservas.service.UserService;
import com.alquiler.reservas.util.EmailSenderService;
import com.alquiler.reservas.util.SpringCoder;



@Controller
public class LoginController {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired 
	UserService userService;
	
	SpringCoder dcode;
	
	EmailSenderService email = new EmailSenderService();
	
	//Rama mantenimiento
	@Autowired
	MantenimientoController mantenimiento;
	
	@GetMapping("/home")
	public String home(Model model) throws Exception {
		System.out.println("HOME");

		
		
		return "home";
	}

	@GetMapping("/signup")
	public String signup(Model model) {
		
		// Comprobar si hay roles
		Role rol = roleRepository.findByName("cl");
		if ( rol == null) {
			rol = new Role();
			rol.setName("cl");
			rol.seteDscripcion("Rol Cliente");
			roleRepository.save(rol);
		}
		List <Role> roles = Arrays.asList(rol);
		model.addAttribute("userForm", new User());
		model.addAttribute("roles",roles);
		model.addAttribute("signup", true);
		
		return "security/user-form/user-signup";
	}
	
	
	@PostMapping("/signup")
	public String signupPost(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) {

		User doneUser = null;
		
		Role rol = roleRepository.findByName("cl");
		List <Role> roles = Arrays.asList(rol);
		model.addAttribute("userForm", user);
		model.addAttribute("roles",roles);
		model.addAttribute("signup", true);	
		
		
		if(result.hasErrors()) {
			List<ObjectError>  e = result.getAllErrors();
			System.out.println( e);
			System.out.println(" Error signup");
			return "security/user-form/user-signup";
		}else {
			try {
				user.setStatus(1);// mejora en la vista // mod temporal para activar el usuario creado sin validación
				
				System.out.println(" Creando user...>> "+user.toString());
				doneUser = userService.createUser(user);
				//userService.deleteUser(user.getId());  test Para dejar de crear usuarios a lo loco
				System.out.println(" [Hecho]");
				String host= "https://rhulk.herokuapp.com";
				String cifrado= dcode.cifrar(user.getUsername());
				String url= host+"/active/"+ cifrado +"/1"; //String url= host+"/active/"+user.getId()+"/1";
				
				email.send("Activar Cuenta", url, "default"); //Mail activación cuenta.
				//email.testMail(); // test ssl
				System.out.println(" [Send Mail Activation]");
				
			}catch (CustomeFieldValidationException cfve) {
				result.rejectValue(cfve.getFieldName(), null, cfve.getMessage());
				try {
					userService.deleteUser(doneUser.getId()); //lo borro para forzar volver a crear el mismo user desde la misma vista.
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return "security/user-form/user-signup";
			}
			catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
				e.printStackTrace();
				System.out.println("[Error] "+e.getMessage());
				System.out.println("[Error] "+e.toString());
				
				return "security/user-form/user-signup";
			}
		}
		//return "security/user-form/user-signup";
		return "index"; //Para dejar de crear usuarios a lo loco
	}
	/*
	 *  Metodo para validar el formulario de Altas o modificaciones de User
	 * 
	 * 
	 *  
	 */
	
	@PostMapping("/userForm")
	public String createUser(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			model.addAttribute("userForm", user);
			model.addAttribute("formTabUser","active");
		}else {
			try {
				userService.createUser(user);
				model.addAttribute("userForm", new User());
				model.addAttribute("listTabUser","active");
				
			}catch (CustomeFieldValidationException cfve) {
				result.rejectValue(cfve.getFieldName(), null, cfve.getMessage());//Controlar si se pierde la sesión org.thymeleaf.exceptions.TemplateInputException: An error happened during template parsing (template: "class path resource [templates/security/user-form/main-view.html]")
				model.addAttribute("userForm", user);
				model.addAttribute("formTabUser","active");
				model.addAttribute("userList", userService.getAllUsers());
				model.addAttribute("roles",roleRepository.findAll());
			}
			catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
				model.addAttribute("userForm", user);
				model.addAttribute("formTabUser","active");
				model.addAttribute("userList", userService.getAllUsers());
				model.addAttribute("roles",roleRepository.findAll());
			}
		}
		
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("roles",roleRepository.findAll());
		model.addAttribute("activoFormTodo",false);
		return "security/user-form/main-view";
	}
	@GetMapping("/AltaUser")
	public String getAltaUser(Model model) {
		
		//Contenido
		model.addAttribute("userForm", new User());
		model.addAttribute("roles",roleRepository.findAll());
		
		//tab active
		model.addAttribute("tabUser", "active");
		model.addAttribute("formTabUser", "active");
		model.addAttribute("listTabUser", "no");
		
		
		//Formularios
		model.addAttribute("activoFormUser",true);
		model.addAttribute("activoFormTodo",false);
		model.addAttribute("editModeUser",false);
		
		return "security/user-form/main-view";
	}
	
	@GetMapping("/listUser")		// TODO: change name for listUser and reorder ok -Testeo-
	public String getListUser(Model model) {
		
		// Gestión del contenido de la pag

		model.addAttribute("roles",roleRepository.findAll());
		model.addAttribute("userList", userService.getAllUsers());
		
		// Gestión de los tab o menus a mostrar
		model.addAttribute("tabUser", "active");
		model.addAttribute("listTabUser","active");
		model.addAttribute("tabSql","No");
		model.addAttribute("tabToDo","No");
		
		// Gestión de la activación de los formularios
		model.addAttribute("activoFormTodo",false);
		model.addAttribute("activoFormUser",false);
		
		//Control model delete
		model.addAttribute("deleteTodo",false);
		model.addAttribute("deleteUser",true);
		
		return "security/user-form/main-view";	
	}
	
	@GetMapping("/editUser/{id}")
	public String getEditUserForm(Model model, @PathVariable(name="id") Long id) throws Exception {
		User user = userService.getUserById(id);
		
		// Gestion de los modulos a visualizar
		model.addAttribute("activoFormTodo",false); // mejor no cargar la parte de todo para optimizar rendimiento.
		model.addAttribute("activoFormUser",true);
		
		
		model.addAttribute("userForm", user);
		model.addAttribute("formTabUser","active");//Activa el tab del formulario.
		
		model.addAttribute("editModeUser",true);//Mira siguiente seccion para mas informacion
		model.addAttribute("passwordForm",new ChangePasswordForm(user.getId()));
		
		return "security/user-form/main-view";
	}
	
	@PostMapping("/editUser")
	public String postEditUserForm(@Valid @ModelAttribute("userForm")User user,
				BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			model.addAttribute("userForm", user);
			model.addAttribute("formTabUser","active");
			model.addAttribute("editModeUser","true");
		}else {
			try {
				
				userService.updateUser(user);
				model.addAttribute("tabUser","active"); 
				model.addAttribute("listTabUser","active");  //TODO: Sobra ? 
			} catch (Exception e) {
				System.out.println("\n Log: "+e.toString()+"\n");
				model.addAttribute("formErrorMessage",e.getMessage());
				model.addAttribute("userForm", user);
				model.addAttribute("formTabUser","active");
				model.addAttribute("userList", userService.getAllUsers());
				model.addAttribute("roles",roleRepository.findAll());
				model.addAttribute("editModeUser","true");
				model.addAttribute("passwordForm",new ChangePasswordForm(user.getId()));
			}
		}
		
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("roles",roleRepository.findAll());
		return "security/user-form/main-view";
		
	}
	
	@PostMapping("/editUser/changePassword")
	public ResponseEntity postEditUseChangePassword(@Valid @RequestBody ChangePasswordForm form, Errors errors) {
		try {
			//If error, just return a 400 bad request, along with the error message
	        if (errors.hasErrors()) {
	            String result = errors.getAllErrors()
	                        .stream().map(x -> x.getDefaultMessage())
	                        .collect(Collectors.joining(""));

	            throw new Exception(result);
	        }
			userService.changePassword(form);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok("success");
	}	

	@GetMapping("/deleteUser/{id}")
	public String deleteUser(Model model, @PathVariable(name="id") Long id) {
		try {
			userService.deleteUser(id);
		} catch (Exception e) {
			// test problema borrando 
			
			model.addAttribute("deleteError","The user could not be deleted.");
		}
		return getListUser(model);  // TODO: getUserForm now 
	}
	/*
	 * Metodo para activar user desde el correo tras ser creado.
	 * 
	 * In delop up
	 * 
	 * Interno
	 * 
	 */
	@GetMapping("/active_/{id}/{status}")
	public String modStatusUserSimple(Model model, @PathVariable(name="id") Long id, @PathVariable(name="status") int status) {
		try {
			userService.modStatusUser(id, status);

		} catch (Exception e) {
			// test problema borrando 
			System.out.println(" -- Error -- To -- modStatusUserSimple -- "+e.toString());
			model.addAttribute("modStatusUserError","The user could not be Actived.");
		}
		return getListUser(model);

	}
	
	/*
	 * Metodo para activar user desde el correo tras ser creado.
	 * 
	 * In delop
	 * 
	 * Modificación con cifrado
	 * 
	 */
	@GetMapping("/active/{cadenaCifrada}/{status}")
	public String modStatusUser(Model model, @PathVariable(name="cadenaCifrada") String cadenaCifrada, @PathVariable(name="status") int status) {
		try {
			userService.modStatusUser(userService.getUserByName(dcode.descifrar(cadenaCifrada)).getId(), status);
			System.out.println("[ Activado ] "+dcode.descifrar(cadenaCifrada));
		} catch (Exception e) {
			// test problema borrando 
			System.out.println(" -- Error -- To -- modStatusUser -- "+e.toString());
			model.addAttribute("ErrorMessage","The user could not be Actived: \n"+e.getMessage());
		}
		return "index";

	}
	
	@GetMapping("/editUser/cancel")
	public String cancelEditUser(ModelMap model) {
		return "redirect:/listUser";
	}
	
	@GetMapping("/")
	public String index() {
		return "home";
	}
	
	@GetMapping("/login")
	public String login(Model model) throws Exception {
		
		// validamos la existencia de usuarios en la bbdd
		if (userService.getAllUsers() == null) {
			System.out.println("Create User default in Home");
		 return	mantenimiento.defaultUsert(model);
		}else {
			System.out.println(" Not user null");
		}
		
		return "index";
	}
	/*
	@GetMapping("/admin/dashboard")
	public String dashboardAdmin() {
		return "dashboardAmin";
	}

	@GetMapping("/user/dashboard")
	public String dashboardUser() {
		return "dashboardUser";
	}
	
	@GetMapping("/user")
	public String user() {
		return "security/user-form/main-view";
	}
	
	@GetMapping("/accessdenied")
	public String accessdenied() {
		return "accessdenied";
	}
	*/
}

