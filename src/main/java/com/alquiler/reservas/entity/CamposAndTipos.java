package com.alquiler.reservas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name="stg_campos")
public class CamposAndTipos {

	//@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	//private Long id;
	
	@Column
	public String campo;
	

	public CamposAndTipos() {

	}
	public CamposAndTipos(String campo, String tipo) {
		this.campo=campo;
		this.tipo=tipo;
	}
/*	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}*/
	@Column
	public String tipo;
	public String getCampo() {
		return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "CamposAndTipos [campo=" + campo + ", tipo=" + tipo + "]";
	}
	
	
	
}
