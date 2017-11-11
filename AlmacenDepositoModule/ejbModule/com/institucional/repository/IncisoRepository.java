package com.institucional.repository;

import java.util.ArrayList;
import java.util.List;

import com.application.repository.Repositorio;
import com.institucional.entities.Inciso;
import com.institucional.entities.PParcial;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class IncisoRepository
 */
@Stateless
@LocalBean
public class IncisoRepository implements Repositorio<Integer, Inciso> {
	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(Inciso newOne) {
		entityManager.persist(newOne);
	}

	@Override
	public void remove(Inciso toDelete) {
		entityManager.remove(toDelete);
	}

	public Inciso get(Integer id) {
		return entityManager.find(Inciso.class, id);
	}

	@Override
	public List<Inciso> getAll() {
		String q = "SELECT p from " + Inciso.class.getName() + " p ";
		TypedQuery<Inciso> query = entityManager.createQuery(q, Inciso.class);

		List<Inciso> result = query.getResultList();
		if (result == null) {
			result = new ArrayList<Inciso>();
		}
		return result;
	}

	@Override
	public long size() {
		String q = "SELECT count(p) from " + Inciso.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}

	public List<Inciso> get2y4() {
		String q = "SELECT p from " + Inciso.class.getName() + " p where nroInciso = 2 or nroInciso = 4";
		TypedQuery<Inciso> query = entityManager.createQuery(q, Inciso.class);

		List<Inciso> result = query.getResultList();
		if (result == null) {
			result = new ArrayList<Inciso>();
		}
		return result;
	}
}
