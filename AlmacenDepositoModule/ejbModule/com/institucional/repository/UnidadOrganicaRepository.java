package com.institucional.repository;

import java.util.ArrayList;
import java.util.List;

import com.application.repository.Repositorio;
import com.institucional.entities.UnidadOrganica;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class UnidadOrganicaRepository
 */
@Stateless
@LocalBean
public class UnidadOrganicaRepository implements Repositorio <Integer, UnidadOrganica> {

	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(UnidadOrganica newOne) {
		entityManager.persist(newOne);
	}

	@Override
	public void remove(UnidadOrganica toDelete) {
		entityManager.remove(toDelete);
	}

	public UnidadOrganica get(Integer id) {
		return entityManager.find(UnidadOrganica.class, id);
	}

	@Override
	public List<UnidadOrganica> getAll() {
		String q = "SELECT p from " + UnidadOrganica.class.getName() + " p ";
		TypedQuery<UnidadOrganica> query = entityManager.createQuery(q, UnidadOrganica.class);
		
		List<UnidadOrganica> result = query.getResultList();
		if(result == null) {
			result = new ArrayList<UnidadOrganica>();
		}
		return result;
	}

	@Override
	public long size() {
		String q = "SELECT count(p) from " + UnidadOrganica.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}

}
