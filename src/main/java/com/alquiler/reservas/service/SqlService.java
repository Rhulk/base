package com.alquiler.reservas.service;

import java.util.List;

public interface SqlService {
	
	public List<String> getCampos(String query);

	boolean desbloqueo();

}
