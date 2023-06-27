package com.alquiler.reservas.service;

import java.util.List;

public interface SqlService {
	
	public List<String> getCampos(String table);

	boolean desbloqueo();

	public List<String> getAllTablas();

}
