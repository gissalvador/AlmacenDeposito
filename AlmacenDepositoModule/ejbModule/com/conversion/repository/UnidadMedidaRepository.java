package com.conversion.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.application.repository.Repositorio;
import com.conversion.entities.UnidadMedida;

/**
 * Session Bean implementation class UnidadMedidaRepository
 */
@Stateless
@LocalBean
public class UnidadMedidaRepository implements Repositorio<Integer, UnidadMedida> {
	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(UnidadMedida newOne) {
		entityManager.persist(newOne);
	}

	@Override
	public void remove(UnidadMedida toDelete) {
		entityManager.remove(toDelete);
	}

	public UnidadMedida get(Integer id) {
		return entityManager.find(UnidadMedida.class, id);
	}

	@Override
	public List<UnidadMedida> getAll() {
		String q = "SELECT p from " + UnidadMedida.class.getName() + " p ";
		TypedQuery<UnidadMedida> query = entityManager.createQuery(q, UnidadMedida.class);
		
		List<UnidadMedida> result = query.getResultList();
		if(result == null) {
			result = new ArrayList<UnidadMedida>();
		}
		return result;
	}

	@Override
	public long size() {
		String q = "SELECT count(p) from " + UnidadMedida.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}

}
