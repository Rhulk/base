package com.alquiler.reservas.service;

import java.util.List;

import com.alquiler.reservas.dto.ApartadoDTO;
import com.alquiler.reservas.dto.CapituloDTO;
import com.alquiler.reservas.entity.Apartado;
import com.alquiler.reservas.entity.Apunte;
import com.alquiler.reservas.entity.Capitulo;
import com.alquiler.reservas.entity.CategoriaCurso;
import com.alquiler.reservas.entity.Checkout;
import com.alquiler.reservas.entity.Curso;
import com.alquiler.reservas.entity.Respuesta;
import com.alquiler.reservas.entity.User;

public interface CursoService {

	 
	/* public List<Curso> getAll(); */
	
	public Respuesta altaCurso(Curso curso);
	public Respuesta modCurso(Curso curso);
	public Respuesta modCapitulo(Capitulo capitulo);
	public Respuesta modApartado(Apartado apartado);
	public boolean addCapitulo(int curso, String nombre, String descripcion, int orden);
	
	public Curso getCurso(int id) throws Exception;
	
	public List<Curso> getCursosByLoguinUser();

	List<Capitulo> getCapitulos(Curso curso);
	
	List<Apartado> getApartados(Curso curso);
	List<ApartadoDTO> getApartadosByCap(Long capitulo);
	List<CapituloDTO> getCapitulosByCurso(int curso);
	public Respuesta addApartado(Long capitulo, String nombre, String descripcion, String recurso, Integer orden);

	public List <Apunte> getApunte(Long apartado,int pag, Integer curso);
	public int getCantidadAportesByApartadoAndCurso(Long apartado, Integer curso);
	
	
	public Apunte getApunteByIdAndCurso(Long apunte, Integer idCurso);
	public boolean modApunte(Long apunte, String notas, Integer idCurso);
	public boolean deleteApunte(Apunte apu);
	public boolean createNewAporte(Long apartado, String notas, Long idUser, Integer idCurso);
	
	public boolean checking(Long apartado, boolean check, Long idUser, Integer idCurso);
	public Checkout getCheckoutByApartadoAndUserAndCurso(Long apartado, Long idUser, Integer idCurso);
	
	public boolean followCurso(Integer curso);
	public boolean unfollowCurso(Integer curso);
	public boolean isFollowCurso(Integer curso);
	
	public Respuesta deleteCapitulo(Long id);
	public Respuesta deleteApartado(Long id);


	
	
}
