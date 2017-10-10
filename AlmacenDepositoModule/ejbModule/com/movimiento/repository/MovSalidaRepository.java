package com.movimiento.repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.application.repository.Repositorio;
import com.movimiento.entities.MovSalida;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class MovSalidaRepository
 */
@Stateless
@LocalBean
public class MovSalidaRepository implements Repositorio <String, MovSalida> {

	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(MovSalida newOne) {
		Long l = (long)(System.nanoTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(new Date()); 
		String id = date;
		id= id.concat(l.toString());
		newOne.setCodMovSalida(id);		
		entityManager.persist(newOne);
	}

	@Override
	public void remove(MovSalida toDelete) {
		entityManager.remove(toDelete);
	}

	public MovSalida get(String id) {
		return entityManager.find(MovSalida.class, id);
	}

	@Override
	public List<MovSalida> getAll() {
		String q = "SELECT p from " + MovSalida.class.getName() + " p ORDER BY fechaCreado DESC";
		TypedQuery<MovSalida> query = entityManager.createQuery(q, MovSalida.class);
		
		List<MovSalida> result = query.getResultList();
		if(result == null) {
			result = new ArrayList<MovSalida>();
		}
		return result;
	}

	@Override
	public long size() {
		String q = "SELECT count(p) from " + MovSalida.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}

}
