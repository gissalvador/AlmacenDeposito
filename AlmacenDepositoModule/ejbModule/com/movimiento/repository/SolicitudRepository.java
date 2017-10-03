package com.movimiento.repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.application.repository.Repositorio;
import com.movimiento.entities.Solicitud;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class SolicitudRepository
 */
@Stateless
@LocalBean
public class SolicitudRepository implements Repositorio <String, Solicitud> {

	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(Solicitud newOne) {
		Long l = (long)(System.nanoTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(new Date()); 
		String id = date;
		id= id.concat(l.toString());
		newOne.setCodSolicitud(id);
		entityManager.persist(newOne);
	}

	@Override
	public void remove(Solicitud toDelete) {
		entityManager.remove(toDelete);
	}

	public Solicitud get(String id) {
		return entityManager.find(Solicitud.class, id);
	}
	
	public String getNroSolicitud(Integer nroSolicitud){
		  //create an ejbql expression
	      String ejbQL = "From Solicitud s where s.nroPedidoCompra = ?1";
	      //create query
	      Query query = entityManager.createQuery(ejbQL);
	      //substitute parameter.
	      
	      query.setParameter(1, "%nroSolicitud%"); 
	      
	      try{
	    	  
	      Solicitud s = new Solicitud();
	   
	      //execute the query
	      
	      s = (Solicitud) query.getSingleResult();
	      
	      return s.getCodSolicitud();
	      
	      } catch( Exception e){
	    	  
	    	  return null;
	      }
	}

	@Override
	public List<Solicitud> getAll() {
		String q = "SELECT p from " + Solicitud.class.getName() + " p ";
		TypedQuery<Solicitud> query = entityManager.createQuery(q, Solicitud.class);
		
		List<Solicitud> result = query.getResultList();
		if(result == null) {
			result = new ArrayList<Solicitud>();
		}
		return result;
	}

	@Override
	public long size() {
		String q = "SELECT count(p) from " + Solicitud.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}

}
