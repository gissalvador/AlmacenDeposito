package com.institucional.repository;

import java.util.ArrayList;
import java.util.List;

import com.application.repository.Repositorio;
import com.institucional.entities.TipoPersona;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class TipoPersonaRepository
 */
@Stateless
@LocalBean
public class TipoPersonaRepository implements Repositorio <Integer, TipoPersona> {

	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(TipoPersona newOne) {
		entityManager.persist(newOne);
	}

	@Override
	public void remove(TipoPersona toDelete) {
		entityManager.remove(toDelete);
	}

	public TipoPersona get(Integer id) {
		return entityManager.find(TipoPersona.class, id);
	}

	@Override
	public List<TipoPersona> getAll() {
		String q = "SELECT p from " + TipoPersona.class.getName() + " p ";
		TypedQuery<TipoPersona> query = entityManager.createQuery(q, TipoPersona.class);
		
		List<TipoPersona> result = query.getResultList();
		if(result == null) {
			result = new ArrayList<TipoPersona>();
		}
		return result;
	}

	@Override
	public long size() {
		String q = "SELECT count(p) from " + TipoPersona.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}

}
