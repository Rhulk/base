package com.alquiler.reservas.entity;

public enum Prioridad {

	ALTA(3,"Alta"),MEDIA(2,"Media"),BAJA(1,"Baja");
	
	Prioridad(int id, String nombre){
		this.id=id;this.nombre=nombre;
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
