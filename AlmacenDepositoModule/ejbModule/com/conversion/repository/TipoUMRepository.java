package com.conversion.repository;

import java.util.ArrayList;
import java.util.List;

import com.application.repository.Repositorio;
import com.conversion.entities.TipoUM;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class TipoUMRepository
 */
@Stateless
@Local(Repositorio.class)
@LocalBean
public class TipoUMRepository implements  Repositorio<Integer, TipoUM> {
	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(TipoUM newOne) {
		entityManager.persist(newOne);
	}

	@Override
	public void remove(TipoUM toDelete) {
		entityManager.remove(toDelete);
	}

	public TipoUM get(Integer id) {
		return entityManager.find(TipoUM.class, id);
	}

	@Override
	public List<TipoUM> getAll() {
		String q = "SELECT p from " + TipoUM.class.getName() + " p ";
		TypedQuery<TipoUM> query = entityManager.createQuery(q, TipoUM.class);
		
		List<TipoUM> result = query.getResultList();
		if(result == null) {
			result = new ArrayList<TipoUM>();
		}
		return result;
	}

	@Override
	public long size() {
		String q = "SELECT count(p) from " + TipoUM.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}

}
