package com.institucional.repository;

import java.util.ArrayList;
import java.util.List;

import com.application.repository.Repositorio;
import com.institucional.entities.PPrincipal;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class PPrincipalRepository
 */
@Stateless
@LocalBean
public class PPrincipalRepository implements Repositorio<Integer, PPrincipal> {
	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(PPrincipal newOne) {
		entityManager.persist(newOne);
	}

	@Override
	public void remove(PPrincipal toDelete) {
		entityManager.remove(toDelete);
	}

	public PPrincipal get(Integer id) {
		return entityManager.find(PPrincipal.class, id);
	}

	@Override
	public List<PPrincipal> getAll() {
		String q = "SELECT p from " + PPrincipal.class.getName() + " p ";
		TypedQuery<PPrincipal> query = entityManager.createQuery(q, PPrincipal.class);
		
		List<PPrincipal> result = query.getResultList();
		if(result == null) {
			result = new ArrayList<PPrincipal>();
		}
		return result;
	}

	@Override
	public long size() {
		String q = "SELECT count(p) from " + PPrincipal.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}
}
