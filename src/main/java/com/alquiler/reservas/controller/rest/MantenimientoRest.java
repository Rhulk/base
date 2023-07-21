package com.alquiler.reservas.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.alquiler.reservas.entity.CamposAndTipos;
import com.alquiler.reservas.service.SqlService;
import com.alquiler.reservas.service.UserService;
import com.google.gson.Gson;

@RestController
public class MantenimientoRest {

	@Autowired
	SqlService sqlService;
	
	@Autowired 
	UserService userService;
	
	@GetMapping("/campos/{tabla}")
	public List<CamposAndTipos> getCampos(@PathVariable(name="tabla") String tabla) {
		
		//System.out.println(sqlService.getCampos(tabla));		
		//System.out.println("TEST: "+tabla);
		return sqlService.getCampos(tabla);
	}
	@GetMapping("/tablas")
	public List<String> getTablas(){
		
		return sqlService.getAllTablas();
	}
}
