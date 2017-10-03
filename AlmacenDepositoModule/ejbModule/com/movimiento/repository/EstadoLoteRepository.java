package com.movimiento.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.application.repository.Repositorio;
import com.movimiento.entities.EstadoLote;


/**
 * Session Bean implementation class EstadoLote
 */
@Stateless
@LocalBean
public class EstadoLoteRepository implements Repositorio<Integer, EstadoLote> {

	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(EstadoLote newOne){
		entityManager.persist(newOne);
	}

	@Override
	public void remove(EstadoLote toDelete){
		entityManager.remove(toDelete);
	}

	@Override
	public EstadoLote get(Integer id) {
		return entityManager.find(EstadoLote.class, id);
	}

	@Override
	public List<EstadoLote> getAll() {
		String q = "SELECT p from " + EstadoLote.class.getName() + " p";
		TypedQuery<EstadoLote> query = entityManager.createQuery(q, EstadoLote.class);
		
		List<EstadoLote> result = query.getResultList();
		if (result == null){
			result = new ArrayList<EstadoLote>();
		}
		return null;
	}

	@Override
	public long size() {
		String q = "SELECT count(p) from " + EstadoLote.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}

}
