package com.alquiler.reservas.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="stg_todo")
public class Todo implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@NotBlank
	private String descripcion;
	
	//estado
	@Column
	//@NotBlank
	private Estado estado;
	
	
	
	@Column
	//@NotBlank	
	private int prioridad;
	
	//tipo
	@Column
	//@NotBlank
	//private Tipo tipo;
	private int tipo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	/*
	 * public Tipo getTipo() { return tipo; }
	 */
	public int getTipo() {
		return tipo;
	}

	/*
	 * public void setTipo(Tipo tipo) { this.tipo = tipo; }
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", descripcion=" + descripcion + ", estado=" + estado + ", prioridad=" + prioridad
				+ ", tipo=" + tipo + "]";
	}

	public Todo(@NotBlank Estado estado) {
		super();
		this.estado = estado;
	}

	public Todo() {
		super();
	}
	
	

}
