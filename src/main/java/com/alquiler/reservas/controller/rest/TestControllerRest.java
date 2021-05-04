package com.alquiler.reservas.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestControllerRest {

	@GetMapping("/testRest")
	public String testRest() {
		return "Test rest controller: proyec reserva";
	}
}
