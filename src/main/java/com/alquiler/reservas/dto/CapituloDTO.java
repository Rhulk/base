package com.alquiler.reservas.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

public class CapituloDTO {
	
	public Long id;
	public	String nombre;
	public String descripcion;
	public int orden;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
	public CapituloDTO(Long id, String nombre, String descripcion, int orden) {

		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.orden = orden;
	}
	public CapituloDTO() {
	}
	
	
}
