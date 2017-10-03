package com.movimiento.repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.application.repository.Repositorio;
import com.movimiento.entities.MovEntrada;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class MovEntradaRepository
 */
@Stateless
@LocalBean
public class MovEntradaRepository implements Repositorio <String, MovEntrada> {

	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(MovEntrada newOne) {
		Long l = (long)(System.nanoTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(new Date()); 
		String id = date;
		id= id.concat(l.toString());
		newOne.setCodMovEntrada(id);		
		entityManager.persist(newOne);
	}

	@Override
	public void remove(MovEntrada toDelete) {
		entityManager.remove(toDelete);
	}

	public MovEntrada get(String id) {
		return entityManager.find(MovEntrada.class, id);
	}

	@Override
	public List<MovEntrada> getAll() {
		String q = "SELECT p from " + MovEntrada.class.getName() + " p ORDER BY fechaCreado DESC";
		TypedQuery<MovEntrada> query = entityManager.createQuery(q, MovEntrada.class);
		
		List<MovEntrada> result = query.getResultList();
		if(result == null) {
			result = new ArrayList<MovEntrada>();
		}
		return result;
	}

	@Override
	public long size() {
		String q = "SELECT count(p) from " + MovEntrada.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}

}
