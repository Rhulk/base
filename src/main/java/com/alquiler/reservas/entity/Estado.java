package com.alquiler.reservas.entity;

public enum Estado {

	// importante que empiece en 0 para que no se lie al pintarlo en el zul
	Inicial(0,"Iniciado"),EnProceso(1,"EnProceso"),Pausado(2,"Pausado"),Resuelto(3,"Resuelto"), All(4,"Recupera todos"),Activos(5,"Todos menos resuelto");
	

	
	private Estado(int id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}
	private int id;
	private String descripcion;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
