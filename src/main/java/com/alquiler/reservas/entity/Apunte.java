package com.alquiler.reservas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="stg_apunte")
public class Apunte {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long id;	

	
	public String notas;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idapartado")
	public Apartado apartado;
	
	@Column
	//@NotBlank pendiente vinculo con el usuario
	public Long iduser;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idcurso")
	public Curso curso;




	public Apunte(String notas, Apartado apartado, Long iduser, Curso curso) {
		super();
		this.notas = notas;
		this.apartado = apartado;
		this.iduser = iduser;
		this.curso = curso;
	}


	public Apunte() {

	}


	public Apunte(String notas) {
		this.notas = notas;
	}

	
	public Apunte(long id, String notas) {
		super();
		this.id = id;
		this.notas = notas;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}



	public Long getIduser() {
		return iduser;
	}

	public void setIduser(Long iduser) {
		this.iduser = iduser;
	}

	public Apartado getApartado() {
		return apartado;
	}

	public void setApartado(Apartado apartado) {
		this.apartado = apartado;
	}

	@Override
	public String toString() {
		return "Apunte [id=" + id + ", notas=" + notas + ", apartado=" + apartado + ", iduser=" + iduser + "]";
	}






	
	
	
	
}
