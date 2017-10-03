package com.articulo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.application.repository.Repositorio;
import com.articulo.entities.HistorialEstadoArticulo;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
@Stateless
@LocalBean
public class HistorialEstadoArticuloRepository implements Repositorio<Integer, HistorialEstadoArticulo> {

	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(HistorialEstadoArticulo newOne){
		entityManager.persist(newOne);
	}

	@Override
	public void remove(HistorialEstadoArticulo toDelete){
		entityManager.remove(toDelete);
	}

	@Override
	public HistorialEstadoArticulo get(Integer id) {
		return entityManager.find(HistorialEstadoArticulo.class, id);
	}

	@Override
	public List<HistorialEstadoArticulo> getAll() {
		String q = "SELECT p from " + HistorialEstadoArticulo.class.getName() + " p";
		TypedQuery<HistorialEstadoArticulo> query = entityManager.createQuery(q, HistorialEstadoArticulo.class);
		
		List<HistorialEstadoArticulo> result = query.getResultList();
		if (result == null){
			result = new ArrayList<HistorialEstadoArticulo>();
		}
		return null;
	}

	@Override
	public long size() {
		String q = "SELECT count(p) from " + HistorialEstadoArticulo.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}
}
