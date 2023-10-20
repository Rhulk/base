package com.alquiler.reservas.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="stg_curso_user")
public class CursoUser {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_curso")
	public Curso curso;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_user")
	public User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "CursoUser [id=" + id + ", curso=" + curso + ", user=" + user + "]";
	}

	public CursoUser(Curso curso, User user) {
		this.curso = curso;
		this.user = user;
	}

	public CursoUser() {
	}
	
	
	

}
