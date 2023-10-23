package com.alquiler.reservas.service;

import java.util.List;

import com.alquiler.reservas.entity.Apartado;
import com.alquiler.reservas.entity.Apunte;
import com.alquiler.reservas.entity.Capitulo;
import com.alquiler.reservas.entity.CategoriaCurso;
import com.alquiler.reservas.entity.Checkout;
import com.alquiler.reservas.entity.Curso;

public interface CursoService {

	 
	/* public List<Curso> getAll(); */
	
	public Curso getCurso(Long id) throws Exception;

	List<Capitulo> getCapitulos(Curso curso);
	
	List<Apartado> getApartados(Curso curso);

	public List <Apunte> getApunte(Long apartado,int pag, Long curso);
	public int getCantidadAportesByApartadoAndCurso(Long apartado, Long curso);
	
	
	public Apunte getApunteByIdAndCurso(Long apunte, Long idCurso);
	public boolean modApunte(Long apunte, String notas, Long idCurso);
	public boolean deleteApunte(Apunte apu);
	public boolean createNewAporte(Long apartado, String notas, Long idUser, Long idCurso);
	
	public boolean checking(Long apartado, boolean check, Long idUser, Long idCurso);
	public Checkout getCheckoutByApartadoAndUserAndCurso(Long apartado, Long idUser, Long idCurso);
	
	public boolean followCurso(Long curso);
	public boolean unfollowCurso(Long curso);
	public boolean isFollowCurso(Long curso);

	
	
}
