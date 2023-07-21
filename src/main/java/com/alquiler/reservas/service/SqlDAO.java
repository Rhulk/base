package com.alquiler.reservas.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alquiler.reservas.entity.CamposAndTipos;
import com.google.gson.Gson;

import java.sql.*;

import liquibase.pro.packaged.E;

@Repository
public class SqlDAO implements SqlService {
	
	@PersistenceContext
	private EntityManager em;
	

	@Override
	public List <CamposAndTipos> getCampos(String tabla) {
		
		List <CamposAndTipos> ct = new LinkedList<>();
		/*
		 * // Problema esto funcionaria si creo todas las tablas como entidades
		 * 
		 * ct = em.
		 * createNativeQuery("SELECT '0' AS id, c.name AS campo, TYPE_NAME(c.user_type_id) AS tipo FROM sys.objects AS o JOIN sys.columns AS c  ON o.object_id = c.object_id WHERE o.name =  '"
		 * +tabla+"'" // "WHERE name like :name" , CamposAndTipos.class )
		 * //.setParameter("name", "J%") .getResultList(); ct.clear(); // Opción A?
		 */		
		// Opcion B una consulta para cada campo del select
		//System.out.println("Lista: "+ct.toString());
		 Query qTipos = em.createNativeQuery("SELECT TYPE_NAME(c.user_type_id) AS tipo FROM sys.objects AS o JOIN sys.columns AS c  ON o.object_id = c.object_id WHERE o.name =  '"
		  +tabla+"' order by TYPE_NAME(c.user_type_id) ");
		 List<String> lTipos = new LinkedList<>();
		 lTipos = qTipos.getResultList();
		 
		 Query qCampos = em.createNativeQuery("SELECT c.name AS campo FROM sys.objects AS o JOIN sys.columns AS c  ON o.object_id = c.object_id WHERE o.name =  '"
				  +tabla+"' order by TYPE_NAME(c.user_type_id) ");
		 List<String> lCampos = new LinkedList<>();
		 lCampos = qCampos.getResultList();	
		 
		 
		 
		 for(int i=0; i< qCampos.getResultList().size();i++) {
			 ct.add(new CamposAndTipos(lCampos.get(i),lTipos.get(i)));
			 
		 }
		 
		 for (CamposAndTipos r: ct) {
			 System.out.println(r);
		 }

		 

		/*
		 * ct.size();
		 * 
		 * Query query = em.
		 * createNativeQuery("SELECT c.name AS col_name, TYPE_NAME(c.user_type_id) AS type_name FROM sys.objects AS o JOIN sys.columns AS c  ON o.object_id = c.object_id WHERE o.name =  '"
		 * +tabla+"'"); List<String> lista = new LinkedList<>(); List<List <String>>
		 * list2 = new LinkedList<>(); lista = query.getResultList(); Map<String,Object>
		 * map = query.getHints(); System.out.println(map.get("id"));
		 * 
		 * List <CamposAndTipos> campos = new LinkedList<>(); CamposAndTipos campo = new
		 * CamposAndTipos(); CamposAndTipos campo1 = new CamposAndTipos();
		 * campo1.setCampo(String.valueOf(lista.get(0).hashCode()));
		 * campo.setCampo(String.valueOf(list2.get(0).get(0))); campo.setTipo("Tipo1");
		 * campos.add(campo);
		 * 
		 * campo.setCampo("Campo2"); campo.setTipo("Tipo2"); campos.add(campo); for (int
		 * i=0; i< list2.size(); i++) {
		 * 
		 * 
		 * 
		 * 
		 * }
		 * 
		 * // TEST
		 * 
		 * int route = 0; String direction = "Dir"; List <String> timeEntries = new
		 * LinkedList<>(); String key ="key";
		 * 
		 * 
		 * // Codigo
		 * 
		 * JSONObject timeTable = new JSONObject(); timeTable.put("route", route);
		 * 
		 * JSONObject info = new JSONObject(); info.put("direction", direction);
		 * 
		 * JSONObject stops = new JSONObject(); stops.put("stops_name", key);
		 * 
		 * // TEST 2
		 * 
		 * List<String> listStrings = new ArrayList<String>(); listStrings.add("a");
		 * listStrings.add("b");
		 * 
		 * List<String> listStrings2 = new ArrayList<String>(); listStrings.add("1");
		 * listStrings.add("2");
		 * 
		 * Gson objGson = new Gson(); System.out.println(objGson.toJson(listStrings));
		 * objGson.toJson(listStrings); objGson.toJson(listStrings2);
		 */
        	
/* Fin Test 2
        
        JSONObject arrivals = new JSONObject();
        JSONArray att = new JSONArray();
        JSONArray arrivalMoFr  = new JSONArray();
        	arrivalMoFr.put( listStrings);

        arrivals.put("mon-fri", arrivalMoFr);
        

        arrivals.put("mon-fri", arrivalMoFr);

        stops.put("arrival_time", arrivals);


        info.put("stops", stops);
        timeTable.put("info", info);

        System.out.println(timeTable.toString(3));
        
        */
        
        /*
         * {
   "route": "4",
   "info": {
      "stops": {
         "arrival_time": {"mon-fri": [[
            "05:04",
            "18:41",
            "19:11",
            "19:41",
            "20:11"
         ]]},
         "stops_name": "Heiweg "
      },
      "direction": "Groß Grönau"
   }
}
         * 
         * 
         */
		
		return ct;
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

	@Override
	public List<String> getAllTablas() {
		
		

		
		
		
		Query query = em.createNativeQuery("SELECT table_name, 'peta' AS peto FROM information_schema.tables ");
		List<String> lista = query.getResultList(); 
		ResultSet rs = (ResultSet) query.getResultList(); 
		return query.getResultList();
	}

}
