package com.movimiento.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.application.repository.Repositorio;
import com.movimiento.entities.HistorialEstadoLote;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
@Stateless
@LocalBean
public class HistorialEstadoLoteRepository implements Repositorio<Integer, HistorialEstadoLote> {

	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(HistorialEstadoLote newOne){
		entityManager.persist(newOne);
	}

	@Override
	public void remove(HistorialEstadoLote toDelete){
		entityManager.remove(toDelete);
	}

	@Override
	public HistorialEstadoLote get(Integer id) {
		return entityManager.find(HistorialEstadoLote.class, id);
	}

	@Override
	public List<HistorialEstadoLote> getAll() {
		String q = "SELECT p from " + HistorialEstadoLote.class.getName() + " p";
		TypedQuery<HistorialEstadoLote> query = entityManager.createQuery(q, HistorialEstadoLote.class);
		
		List<HistorialEstadoLote> result = query.getResultList();
		if (result == null){
			result = new ArrayList<HistorialEstadoLote>();
		}
		return null;
	}

	@Override
	public long size() {
		String q = "SELECT count(p) from " + HistorialEstadoLote.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}
}
