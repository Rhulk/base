package com.alquiler.reservas.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="stg_capitulo")
public class Capitulo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;
	
	@Column
	@NotBlank
	public	String nombre;
	
	@Column
	@NotBlank
	public String descripcion;
	
	@Column
	public int orden;
	
	@ManyToOne
	@JoinColumn(name="idcurso") // idcurso en BBDD?
	private Curso curso;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="idcapitulo")
	List<Apartado> apartados;

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

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Apartado> getApartados() {
		return apartados;
	}

	public void setApartados(List<Apartado> apartados) {
		this.apartados = apartados;
	}



	@Override
	public String toString() {
		return " [nombre=" + nombre + ", descripcion=" + descripcion + ", orden=" + orden + "]";
	}

	public Capitulo(Long id, @NotBlank String nombre, @NotBlank String descripcion, @NotBlank int orden) {

		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.orden = orden;
	}

	public Capitulo() {

	}

	public Capitulo(@NotBlank String nombre, @NotBlank String descripcion, int orden) {
		
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.orden = orden;
	}
	
	





	
	
	
	
}
