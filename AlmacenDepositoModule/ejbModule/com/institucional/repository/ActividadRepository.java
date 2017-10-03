package com.institucional.repository;

import java.util.ArrayList;
import java.util.List;

import com.application.repository.Repositorio;
import com.institucional.entities.Actividad;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class ActividadRepository
 */
@Stateless
@LocalBean
public class ActividadRepository implements Repositorio<Integer, Actividad> {

	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(Actividad newOne) {
		entityManager.persist(newOne);
	}

	@Override
	public void remove(Actividad toDelete) {
		entityManager.remove(toDelete);
	}

	public Actividad get(Integer id) {
		return entityManager.find(Actividad.class, id);
	}

	@Override
	public List<Actividad> getAll() {
		String q = "SELECT p from " + Actividad.class.getName() + " p ";
		TypedQuery<Actividad> query = entityManager.createQuery(q, Actividad.class);
		
		List<Actividad> result = query.getResultList();
		if(result == null) {
			result = new ArrayList<Actividad>();
		}
		return result;
	}

	@Override
	public long size() {
		String q = "SELECT count(p) from " + Actividad.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}
	
	public List<Actividad> getActividad(Integer nroActividad) {

		String q = "SELECT p from " + Actividad.class.getName() + " p"
				+ " where nroActividad = " + nroActividad;
		TypedQuery<Actividad> query = entityManager.createQuery(q,
				Actividad.class);

		List<Actividad> result = query.getResultList();
		if (result == null) {
			result = new ArrayList<Actividad>();
		}
		return result;
	}

	public List<Actividad> getNroActividad(Integer nroActividad) {
		// TODO Auto-generated method stub
		String q = "SELECT p from " + Actividad.class.getName() + " p"
				+ " where nroActividad = " + nroActividad ;
		TypedQuery<Actividad> query = entityManager.createQuery(q, Actividad.class);

		List<Actividad> result = query.getResultList();
		if (result == null) {
			result = new ArrayList<Actividad>();
		}
		return result;
	}

}
