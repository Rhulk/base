package com.alquiler.reservas.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SqlDAO implements SqlService {
	
	@PersistenceContext
	private EntityManager em;
	

	@Override
	public List<String> getCampos(String tabla) {
		Query query = em.createNativeQuery("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME ='"+tabla+"'");
		//Query qy = em.createNamedQuery("SELECT * FROM usuario"); //No query defined for that name [SELECT * FROM usuario]
		return query.getResultList();
	}
	
	@Override
	@Transactional
	public boolean desbloqueo() {
		//Query qy = em.createNamedQuery("SELECT * FROM usuario u");	//No query defined for that name [SELECT * FROM usuario]
		//Query sifunciona = em.createNativeQuery("SELECT * FROM usuario u");
		//Query query = em.createNativeQuery("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME ='"+"usuario"+"'");
		
		
		try {
			Query update = em.createNativeQuery("UPDATE databasechangeloglock set locked=false");
//			Query select = em.createNamedQuery("Select * from databasechangeloglock d");
			//Query data = em.createNamedQuery("SELECT * FROM databasechangelog d");
			//data.getResultList();
			//sifunciona.getResultList();
			//select.getResultList();
			
			//update.getResultList();
			return true;
		}catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}

		
	}

}
