package com.alquiler.reservas.service;

import java.util.List;

import com.alquiler.reservas.entity.CamposAndTipos;


public interface SqlService {
	
	public List<CamposAndTipos> getCampos(String table);

	boolean desbloqueo();

	public List<String> getAllTablas();

}
