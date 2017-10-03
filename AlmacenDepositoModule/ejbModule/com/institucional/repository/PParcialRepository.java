package com.institucional.repository;

import java.util.ArrayList;
import java.util.List;

import com.application.repository.Repositorio;
import com.institucional.entities.PParcial;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class PParcialRepository
 */
@Stateless
@LocalBean
public class PParcialRepository implements Repositorio<Integer, PParcial> {
	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(PParcial newOne) {
		entityManager.persist(newOne);
	}

	@Override
	public void remove(PParcial toDelete) {
		entityManager.remove(toDelete);
	}

	public PParcial get(Integer id) {
		return entityManager.find(PParcial.class, id);
	}

	@Override
	public List<PParcial> getAll() {
		String q = "SELECT p from " + PParcial.class.getName() + " p ";
		TypedQuery<PParcial> query = entityManager.createQuery(q,
				PParcial.class);

		List<PParcial> result = query.getResultList();
		if (result == null) {
			result = new ArrayList<PParcial>();
		}
		return result;
	}

	@Override
	public long size() {
		String q = "SELECT count(p) from " + PParcial.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}

	public List<PParcial> getPPa(Integer ppa) {

		String q = "SELECT p from " + PParcial.class.getName() + " p"
				+ " where nroPParcial = " + ppa;
		TypedQuery<PParcial> query = entityManager.createQuery(q,
				PParcial.class);

		List<PParcial> result = query.getResultList();
		if (result == null) {
			result = new ArrayList<PParcial>();
		}
		return result;
	}

}
