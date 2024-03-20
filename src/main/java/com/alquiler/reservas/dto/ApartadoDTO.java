package com.alquiler.reservas.dto;

public class ApartadoDTO {
	
	public Long id;	
	public	String nombre;
	public String descripcion;
	public	String recurso;
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
	public String getRecurso() {
		return recurso;
	}
	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
	@Override
	public String toString() {
		return "ApartadoDTO [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", recurso=" + recurso
				+ ", orden=" + orden + "]";
	}
	
	

}
