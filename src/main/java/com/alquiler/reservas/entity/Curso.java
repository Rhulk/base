package com.alquiler.reservas.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="stg_curso" )
public class Curso {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int id;
	
	@Column
	@NotBlank
	public	String nombre;
	
	@Column
	public CategoriaCurso categoriacurso; 
	
	@Column
	@NotBlank
	public String descripcion;
	
	@Column
	public	String fuente;
	
	@Column
	public	String urlimagen;
	
	@Column
	public	String urlicono;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="idcurso")
	List<Capitulo> capitulos;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idcurso")
	public Apunte apunte;
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
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





	public String getUrlimagen() {
		return urlimagen;
	}

	public void setUrlimagen(String urlimagen) {
		this.urlimagen = urlimagen;
	}

	public String getUrlicono() {
		return urlicono;
	}

	public void setUrlicono(String urlicono) {
		this.urlicono = urlicono;
	}

	public CategoriaCurso getCategoriacurso() {
		return categoriacurso;
	}

	public void setCategoriacurso(CategoriaCurso categoriacurso) {
		this.categoriacurso = categoriacurso;
	}

	public List<Capitulo> getCapitulos() {
		return capitulos;
	}

	public void setCapitulos(List<Capitulo> capitulos) {
		this.capitulos = capitulos;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", nombre=" + nombre + ", categoriacurso=" + categoriacurso + ", descripcion="
				+ descripcion + ", fuente=" + fuente + ", urlimagen=" + urlimagen + ", urlicono=" + urlicono
				+ ", capitulos=" + capitulos + "]";
	}

	public Curso(int id) {
		this.id = id;
	}

	public Curso() {
	}

	public Curso(int id, @NotBlank String nombre, CategoriaCurso categoriacurso, @NotBlank String descripcion,
			String fuente, String urlimagen, String urlicono) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.categoriacurso = categoriacurso;
		this.descripcion = descripcion;
		this.fuente = fuente;
		this.urlimagen = urlimagen;
		this.urlicono = urlicono;
	}
	
	












	
	

}
