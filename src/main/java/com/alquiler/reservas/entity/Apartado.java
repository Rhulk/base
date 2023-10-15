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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="stg_apartado")
public class Apartado {
	

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
	@NotBlank
	public	String recurso;
	
	@Column
	@NotBlank
	public int orden;
	
	@ManyToOne
	@JoinColumn(name="idcapitulo")
	private Capitulo capitulo;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="idapartado")
	public List<Apunte> apuntes;
	
	@OneToOne
	@JoinColumn(name="id_apartado")
	public Checkout checkout;

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

	public Capitulo getCapitulo() {
		return capitulo;
	}

	public void setCapitulo(Capitulo capitulo) {
		this.capitulo = capitulo;
	}

	@Override
	public String toString() {
		return "Apartado [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", recurso=" + recurso
				+ ", orden=" + orden + "]";
	}


	
}
