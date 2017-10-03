package com.movimiento.repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.application.repository.Repositorio;
import com.movimiento.entities.DetalleMovSalida;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class DetalleMSrepository
 */
@Stateless
@LocalBean
public class DetalleMSrepository implements Repositorio <String, DetalleMovSalida> {


	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(DetalleMovSalida newOne) {
		Long l = (long)(System.nanoTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(new Date()); 
		String id = date;
		id= id.concat(l.toString());
		newOne.setCodDetalleMS(id);
		entityManager.persist(newOne);
	}

	@Override
	public void remove(DetalleMovSalida toDelete) {
		entityManager.remove(toDelete);
	}

	public DetalleMovSalida get(String id) {
		return entityManager.find(DetalleMovSalida.class, id);
	}

	@Override
	public List<DetalleMovSalida> getAll() {
		String q = "SELECT p from " + DetalleMovSalida.class.getName() + " p ";
		TypedQuery<DetalleMovSalida> query = entityManager.createQuery(q, DetalleMovSalida.class);
		
		List<DetalleMovSalida> result = query.getResultList();
		if(result == null) {
			result = new ArrayList<DetalleMovSalida>();
		}
		return result;
	}

	@Override
	public long size() {
		String q = "SELECT count(p) from " + DetalleMovSalida.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}

}
