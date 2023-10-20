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

	public List <Apunte> getApunte(Long apartado,int pag);
	public int getCantidadAportesByApartado(Long apartado);
	
	
	public Apunte getApunteById(Long apunte);
	public void deleteApunte(Apunte apu);

	public void createNewAporte(Long apartado, String notas, Long idUser);

	public void checking(Long apartado, boolean check, Long idUser);
	
	public Checkout getCheckoutByApartadoAndUser(Long apartado, Long idUser);
	
	public boolean followCurso(Long curso);
	public boolean unfollowCurso(Long curso);
	public boolean isFollowCurso(Long curso);
	
}
