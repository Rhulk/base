package com.alquiler.reservas.entity;

public enum CategoriaCurso {
	
	BACK("Back-End",1), FRONT("Front-End",2);

	CategoriaCurso(String nombre, int id) {
		this.nombre=nombre;this.id=id;
	}
	
	private int id;
	private String nombre;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	

}
