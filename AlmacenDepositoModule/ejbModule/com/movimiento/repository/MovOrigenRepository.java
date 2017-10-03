package com.movimiento.repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.application.repository.Repositorio;
import com.movimiento.entities.MovOrigen;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.Query;



/**
 * Session Bean implementation class MovOrigenRepository
 */
@Stateless
@LocalBean
public class MovOrigenRepository implements Repositorio <String, MovOrigen> {

	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(MovOrigen newOne) {
		Long l = (long)(System.nanoTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(new Date()); 
		String id = date;
		id= id.concat(l.toString());
		newOne.setCodMovOrigen(id);
		entityManager.persist(newOne);
	}

	@Override
	public void remove(MovOrigen toDelete) {
		entityManager.remove(toDelete);
	}

	public MovOrigen get(String id) {
		return entityManager.find(MovOrigen.class, id);
	}
	
	public String getNroComprobante(String nroComprobante){
		  //create an ejbql expression
	      String ejbQL = "From MovOrigen m where m.nroComprobanteMO = ?1";
	      //create query
	      Query query = entityManager.createQuery(ejbQL);
	      //substitute parameter.
	      
	      query.setParameter(1, "%nroComprobante%"); 
	      
	      try{
	    	  
	      MovOrigen mo = new MovOrigen();
	   
	      //execute the query
	      
	      mo = (MovOrigen) query.getSingleResult();
	      
	      return mo.getCodMovOrigen();
	      
	      } catch( Exception e){
	    	  
	    	  return null;
	      }
	}

	@Override
	public List<MovOrigen> getAll() {
		String q = "SELECT p from " + MovOrigen.class.getName() + " p ";
		TypedQuery<MovOrigen> query = entityManager.createQuery(q, MovOrigen.class);
		
		List<MovOrigen> result = query.getResultList();
		if(result == null) {
			result = new ArrayList<MovOrigen>();
		}
		return result;
	}

	@Override
	public long size() {
		String q = "SELECT count(p) from " + MovOrigen.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}

}
