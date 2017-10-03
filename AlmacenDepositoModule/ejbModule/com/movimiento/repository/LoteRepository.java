package com.movimiento.repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.application.repository.Repositorio;
import com.movimiento.entities.Lote;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class LoteRepository
 */
@Stateless
@LocalBean
public class LoteRepository implements Repositorio <String, Lote> {

	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(Lote newOne) {
		Long l = (long)(System.nanoTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(new Date()); 
		String id = date;
		id= id.concat(l.toString());
		newOne.setCodLote(id);
		entityManager.persist(newOne);
	}

	@Override
	public void remove(Lote toDelete) { 
		entityManager.remove(toDelete);
	}

	public Lote get(String id) {
		return entityManager.find(Lote.class, id);
	}

	@Override
	public List<Lote> getAll() {
		String q = "SELECT p from " + Lote.class.getName() + " p " +  "where stockActual > 0" ;
		TypedQuery<Lote> query = entityManager.createQuery(q, Lote.class);
		
		List<Lote> result = query.getResultList();
		if(result == null) {
			result = new ArrayList<Lote>();
		}
		return result;
	}
	
	
	public List<Lote> buscarlote(Integer articulo) {
		String q = "SELECT p FROM "+ Lote.class.getName() + " p " + " where articulo = " + articulo + " and (estadoActualLote = 1  or estadoActualLote = 2) order by fechaCreado" ;
		TypedQuery<Lote> query = entityManager.createQuery(q, Lote.class);
		
		List<Lote> result = query.getResultList();
		if(result == null) {
			result = new ArrayList<Lote>();
		}
		return result;
	}
	
	public List<Lote> buscarXvto(Integer articulo) {
		String q = "SELECT p FROM "+ Lote.class.getName() + " p " + " where articulo = " + articulo + " and (estadoActualLote = 1  or estadoActualLote = 2) order by fechaVto" ;
		TypedQuery<Lote> query = entityManager.createQuery(q, Lote.class);
		
		List<Lote> result = query.getResultList();
		if(result == null) {
			result = new ArrayList<Lote>();
		}
		return result;
	}
	
	
	@Override
	public long size() {
		String q = "SELECT count(p) from " + Lote.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}

	public List<Lote> buscarXact(Integer articulo, Integer actividad) {
		String q = "SELECT p FROM "+ Lote.class.getName() + " p " + " where articulo = " + articulo + "and actividad = "+ actividad +" and (estadoActualLote = 1  or estadoActualLote = 2) order by fechaCreado" ;
		TypedQuery<Lote> query = entityManager.createQuery(q, Lote.class);
		
		List<Lote> result = query.getResultList();
		if(result == null) {
			result = new ArrayList<Lote>();
		}
		return result;
	}

}
