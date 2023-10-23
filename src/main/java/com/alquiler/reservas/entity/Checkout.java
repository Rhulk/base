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
import javax.validation.constraints.NotBlank;


@Entity
@Table(name="stg_checkout")
public class Checkout {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;
	
	@Column
	public boolean checking;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_apartado")
	public Apartado apartado;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_user")
	public User user;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idcurso")
	public Curso curso;
	
	
	public Checkout() {
		super();
	}

	public Checkout(@NotBlank boolean checking, Apartado apartado, User user,Curso curso) {
		super();
		this.checking = checking;
		this.apartado = apartado;
		this.user = user;
		this.curso = curso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isChecking() {
		return checking;
	}

	public void setChecking(boolean checking) {
		this.checking = checking;
	}

	public Apartado getApartado() {
		return apartado;
	}

	public void setApartado(Apartado apartado) {
		this.apartado = apartado;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Checkout [id=" + id + ", checking=" + checking + ", apartado=" + apartado + ", user=" + user + "]";
	}
	
	
	

}
