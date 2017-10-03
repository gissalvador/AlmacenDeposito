package com.articulo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.application.repository.Repositorio;
import com.articulo.entities.Articulo;

@Stateless
@LocalBean
public class ArticuloRepository implements Repositorio<Integer, Articulo> {
	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(Articulo newOne){
		entityManager.persist(newOne);
	}

	@Override
	public void remove(Articulo toDelete){
		entityManager.remove(toDelete);
	}

	@Override
	public Articulo get(Integer id) {
		return entityManager.find(Articulo.class, id);
	}

	@Override
	public List<Articulo> getAll() {
		String q = "SELECT p from " + Articulo.class.getName() + " p";
		TypedQuery<Articulo> query = entityManager.createQuery(q, Articulo.class);
		
		List<Articulo> result = query.getResultList();
		if (result == null){
			result = new ArrayList<Articulo>();
		}
		return result;
	}

	@Override
	public long size() {
		String q = "SELECT count(p) from " + Articulo.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}

	public List<Articulo> getDesArticulo(String descArticulo) {
		String q = "SELECT p from " + Articulo.class.getName() + " p"
				+ " where descArticulo = '" + descArticulo + "'";
		TypedQuery<Articulo> query = entityManager.createQuery(q, Articulo.class);

		List<Articulo> result = query.getResultList();
		if (result == null) {
			result = new ArrayList<Articulo>();
		}
		return result;
	}

}
