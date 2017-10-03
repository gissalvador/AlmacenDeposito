package com.articulo.repository;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.application.repository.Repositorio;
import com.articulo.entities.EstadoArticulo;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
@Stateless
@LocalBean
public class EstadoArticuloRepository implements Repositorio<Integer, EstadoArticulo> {

	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(EstadoArticulo newOne){
		entityManager.persist(newOne);
	}

	@Override
	public void remove(EstadoArticulo toDelete){
		entityManager.remove(toDelete);
	}

	@Override
	public EstadoArticulo get(Integer id) {
		return entityManager.find(EstadoArticulo.class, id);
	}

	@Override
	public List<EstadoArticulo> getAll() {
		String q = "SELECT p from " + EstadoArticulo.class.getName() + " p";
		TypedQuery<EstadoArticulo> query = entityManager.createQuery(q, EstadoArticulo.class);
		
		List<EstadoArticulo> result = query.getResultList();
		if (result == null){
			result = new ArrayList<EstadoArticulo>();
		}
		return null;
	}

	@Override
	public long size() {
		String q = "SELECT count(p) from " + EstadoArticulo.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}

}
