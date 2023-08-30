package com.alquiler.reservas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="stg_curso")
public class Curso {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@NotBlank
	private	String nombre;
	
	@Column
	@NotBlank
	//private	String categoria; // Mejorable Enum or BBDD
	private CategoriaCurso categoriaCurso;
	
	@Column
	@NotBlank
	private String descripcion;
	
	@Column
	private	String fuente;
	
	@Column
	private	String urlImagen;
	
	@Column
	private	String urlIcono;

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

	public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public String getUrlIcono() {
		return urlIcono;
	}

	public void setUrlIcono(String urlIcono) {
		this.urlIcono = urlIcono;
	}

	public CategoriaCurso getCategoriaCurso() {
		return categoriaCurso;
	}

	public void setCategoriaCurso(CategoriaCurso categoriaCurso) {
		this.categoriaCurso = categoriaCurso;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", nombre=" + nombre + ", categoriaCurso=" + categoriaCurso + ", descripcion="
				+ descripcion + ", fuente=" + fuente + ", urlImagen=" + urlImagen + ", urlIcono=" + urlIcono + "]";
	}


	
	

}
