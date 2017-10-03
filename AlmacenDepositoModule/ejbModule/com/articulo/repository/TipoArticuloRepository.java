package com.articulo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.application.repository.Repositorio;
import com.articulo.entities.TipoArticulo;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
@Stateless
@LocalBean
public class TipoArticuloRepository implements	Repositorio<Integer, TipoArticulo> {

	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(TipoArticulo newOne){
		entityManager.persist(newOne);
	}

	@Override
	public void remove(TipoArticulo toDelete){
		entityManager.remove(toDelete);
	}

	@Override
	public TipoArticulo get(Integer id) {
		return entityManager.find(TipoArticulo.class, id);
	}

	@Override
	public List<TipoArticulo> getAll() {
		String q = "SELECT p from " + TipoArticulo.class.getName() + " p";
		TypedQuery<TipoArticulo> query = entityManager.createQuery(q, TipoArticulo.class);
		
		List<TipoArticulo> result = query.getResultList();
		if (result == null){
			result = new ArrayList<TipoArticulo>();
		}
		return result;
	}

	@Override
	public long size() {
		String q = "SELECT count(p) from " + TipoArticulo.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}
}
