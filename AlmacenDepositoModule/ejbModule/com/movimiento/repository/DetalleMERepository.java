package com.movimiento.repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.application.repository.Repositorio;
import com.movimiento.entities.DetalleMovEntrada;
import com.movimiento.entities.Lote;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class DetalleMovEntradaepository
 */
@Stateless
@LocalBean
public class DetalleMERepository implements Repositorio <String, DetalleMovEntrada> {

	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(DetalleMovEntrada newOne) {
		Long l = (long)(System.nanoTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(new Date()); 
		String id = date;
		id= id.concat(l.toString());
		newOne.setCodDetalleME(id);	
		entityManager.persist(newOne);
	}

	@Override
	public void remove(DetalleMovEntrada toDelete) {
		entityManager.remove(toDelete);
	}

	public DetalleMovEntrada get(String id) {
		return entityManager.find(DetalleMovEntrada.class, id);
	}

	@Override
	public List<DetalleMovEntrada> getAll() {
		String q = "SELECT p from " + DetalleMovEntrada.class.getName() + " p ";
		TypedQuery<DetalleMovEntrada> query = entityManager.createQuery(q, DetalleMovEntrada.class);
		
		List<DetalleMovEntrada> result = query.getResultList();
		if(result == null) {
			result = new ArrayList<DetalleMovEntrada>();
		}
		return result;
	}
	
	public List<DetalleMovEntrada> buscarCodP(String lote) {
		String q = "SELECT p FROM "+ DetalleMovEntrada.class.getName() + " p " + " where lote = " + lote;
	
		
		TypedQuery<DetalleMovEntrada> query = entityManager.createQuery(q, DetalleMovEntrada.class);
				
		List<DetalleMovEntrada> result = query.getResultList();
		if(result == null) {
			result = new ArrayList<DetalleMovEntrada>();
		}
		return result;
	}
	//verrrrrrrrrrrrrrrrrrrrr
	public List<String> buscarxLote(String lote) {
		
		
		 List<String> result = entityManager.createNativeQuery(
			    "SELECT codDetalleME FROM almacendeposito.detallemoventrada where loteId = ?1  order by codDetalleME LIMIT 1")
			    .setParameter(1, lote)
			    .getResultList();
		
		
		
		return result;
	}
	

	@Override
	public long size() {
		String q = "SELECT count(p) from " + DetalleMovEntrada.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}

}
