package com.conversion.repository;

import com.application.repository.Repositorio;


import com.conversion.entities.Conversion;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class ConversionRepository
 */
@Stateless
@Local(Repositorio.class)
@LocalBean
public class ConversionRepository implements  Repositorio<Integer, Conversion> {
	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(Conversion newOne) {
		entityManager.persist(newOne);
	}

	@Override
	public void remove(Conversion toDelete) {
		entityManager.remove(toDelete);
	}

	public Conversion get(Integer id) {
		return entityManager.find(Conversion.class, id);
	}

	@Override
	public List<Conversion> getAll() {
		String q = "SELECT p from " + Conversion.class.getName() + " p ";
		TypedQuery<Conversion> query = entityManager.createQuery(q, Conversion.class);
		
		List<Conversion> result = query.getResultList();
		if(result == null) {
			result = new ArrayList<Conversion>();
		}
		return result;
	}

	@Override
	public long size() {
		String q = "SELECT count(p) from " + Conversion.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}

}
