package com.alquiler.reservas.entity;

public enum Tipo {

	
	DESA("Desarrollo",1), GES("Gestión",2), INT("Integración",3), SUPORT("Soporte",4),TEST("Testeo",5);
	
	Tipo(String nombre, int id) {
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
