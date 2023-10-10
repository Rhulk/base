package com.alquiler.reservas.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.validator.internal.util.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alquiler.reservas.entity.Apunte;

@Repository
public class CursoDAO {
	
	@PersistenceContext
	private EntityManager em;

	
	/*
	 * @Autowired protected SessionFactory sessionFactory;
	 * 
	 * protected Session getCurrentSession() { return
	 * sessionFactory.getCurrentSession(); }
	 */
	
	public List <Apunte> getApunteDAO(Long apartado, int pag){
		List <Apunte> apus = new LinkedList<>();
		List<String> notas = new LinkedList<>();

		List<Integer> ids = new ArrayList<Integer>();
		

		Query nota = em.createNativeQuery("select a.notas from stg_apunte a WHERE idapartado ='"+apartado+"' order by id asc");
		Query id = em.createNativeQuery("select a.id from stg_apunte a WHERE idapartado ='"+apartado+"' order by id asc");

		notas = nota.setFirstResult(pag).setMaxResults(1).getResultList(); 
		ids = id.setFirstResult(pag).setMaxResults(1).getResultList();
		
		apus.add(new Apunte(ids.get(0),notas.get(0)));

		return apus;
	}
	
	public int getCantidadAportesByApartadoDAO(Long apartado) {
		
		Query query = em.createNativeQuery("select count(*) from stg_apunte WHERE idapartado ='"+apartado+"'");

		return (int) query.getResultList().get(0);
	}
	


}
