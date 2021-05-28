package com.alquiler.reservas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.alquiler.reservas.service.UserService;

@RestController
public class TestingRest {
	
	@Autowired 
	UserService userService;

	@GetMapping("/allUser")
	public String getAllUser() {
		//userService.getAllUsers();
		System.out.println(" -- "+ userService.getAllUsers());
		return "";
	}
	
	@GetMapping("/getUser/{id}")
	public String getUserId(@PathVariable(name="id") Long id) throws Exception {
		//userService.getAllUsers();
		System.out.println(" -- "+ userService.getUserById(id));
		return userService.getUserById(id).toString();
	}

	/*
	 *  Evitar Null en status
	 */
	@GetMapping("/ajusteStatus")
	public String ajusteStatus() throws Exception {
		//falta recuperar total user
		
		for( long x=1; x <= 30;x++) {
			//userService.getUserById(x);
			userService.updateUser(userService.getUserById(x));
		}


		return "Hecho";
	}	
	/*
	 *  Evitar Null en status
	 */
	@GetMapping("/ajusteStatus/{id}")
	public String ajusteStatusId(@PathVariable(name="id") Long id) throws Exception {
		//falta recuperar total user

			userService.updateUser(userService.getUserById(id));



		return "Hecho";
	}	
}
