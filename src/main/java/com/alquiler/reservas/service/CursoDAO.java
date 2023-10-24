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
import com.alquiler.reservas.entity.Curso;
import com.alquiler.reservas.entity.User;

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
	
	public List <Apunte> getApunteDAO(Long apartado, int pag, Integer curso){
		List <Apunte> apus = new LinkedList<>();
		List<String> notas = new LinkedList<>();

		List<Integer> ids = new ArrayList<Integer>();
		

		Query nota = em.createNativeQuery("select a.notas from stg_apunte a WHERE idapartado ='"+apartado+"'"
				+ " and  idcurso ='"+curso+"'  order by id asc");
		Query id = em.createNativeQuery("select a.id from stg_apunte a WHERE idapartado ='"+apartado+"'"
				+ " and  idcurso ='"+curso+"'  order by id asc");

		notas = nota.setFirstResult(pag).setMaxResults(1).getResultList(); 
		ids = id.setFirstResult(pag).setMaxResults(1).getResultList();
		
		if(notas.size()>0) {
			apus.add(new Apunte(ids.get(0),notas.get(0)));
		}
		return apus;
	}
	
	public int getCantidadAportesByApartadoAndCursoDAO(Long apartado,Integer curso) {
		
		Query query = em.createNativeQuery(
				"select count(*) from stg_apunte WHERE idapartado ='"+apartado+"' and  idcurso ='"+curso+"'");

		return (int) query.getResultList().get(0);
	}
	
	public List<Curso> findCursosByUser(User user){
		List<Curso> cursos = new LinkedList<>();
		List<Integer> list = new ArrayList<Integer>();

		Query ids = em.createNativeQuery(
				"select c.id from [appSecurity].[dbo].stg_curso c " + 
				"	left join [appSecurity].[dbo].stg_curso_user as cu on cu.id_curso=c.id " + 
				"	 where cu.id_user='"+user.getId()+"'");	
		
		list = ids.getResultList();
		
		for(int i=0; i< list.size();i++) {
			cursos.add(new Curso(list.get(i)));
		}
		return cursos;
	}

}
