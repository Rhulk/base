package com.alquiler.reservas.util;

import java.util.LinkedList;
import java.util.List;

import com.alquiler.reservas.entity.Estado;
import com.alquiler.reservas.entity.Prioridad;
import com.alquiler.reservas.entity.Tipo;

public class Genericos {

	
	
	public List<Estado> getEstadosSelected(){
		List<Estado> estados = new LinkedList<Estado>();
		
		estados.add(Estado.Inicial);
		estados.add(Estado.EnProceso);
		estados.add(Estado.Pausado);
		estados.add(Estado.Resuelto);
		
		return estados;
	}
	
	public List<Estado> getEstadosFiltros(){
		List<Estado> estados = new LinkedList<Estado>();
		
		estados.add(Estado.Inicial);
		estados.add(Estado.EnProceso);
		estados.add(Estado.Pausado);
		estados.add(Estado.Resuelto);
		estados.add(Estado.Activos);
		estados.add(Estado.All);
		
		return estados;
	}
	
	public List<Tipo> getAllTipos(){
		List<Tipo> tipos = new LinkedList<Tipo>();
		
		tipos.add(Tipo.DESA);
		tipos.add(Tipo.GES);
		tipos.add(Tipo.INT);
		tipos.add(Tipo.SUPORT);
		tipos.add(Tipo.TEST);
		
		return tipos;
	}
	
	
	public List<Prioridad> getAllPrioridades(){
		List<Prioridad> prioridades = new LinkedList<Prioridad>();
		//Prioridad prio = null;
		prioridades.add(Prioridad.ALTA);
		prioridades.add(Prioridad.MEDIA);
		prioridades.add(Prioridad.BAJA);
		//prio.setNombre("Otro");
		//prio.setId(0);
		
		//prioridades.add(prio);
		
		return prioridades;
	}
}
