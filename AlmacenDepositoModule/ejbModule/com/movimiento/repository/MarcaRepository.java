package com.movimiento.repository;

import java.util.ArrayList;
import java.util.List;

import com.application.repository.Repositorio;
import com.movimiento.entities.Marca;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class MarcaRepository
 */
@Stateless
@LocalBean
public class MarcaRepository implements Repositorio <Integer, Marca> {

	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(Marca newOne) {
		entityManager.persist(newOne);
	}

	@Override
	public void remove(Marca toDelete) {
		entityManager.remove(toDelete);
	}

	public Marca get(Integer id) {
		return entityManager.find(Marca.class, id);
	}

	@Override
	public List<Marca> getAll() {
		String q = "SELECT p from " + Marca.class.getName() + " p ";
		TypedQuery<Marca> query = entityManager.createQuery(q, Marca.class);
		
		List<Marca> result = query.getResultList();
		if(result == null) {
			result = new ArrayList<Marca>();
		}
		return result;
	}

	@Override
	public long size() {
		String q = "SELECT count(p) from " + Marca.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}

	public List<Marca> getNomMarca(String nomMarca) {
		// TODO Auto-generated method stub
		String q = "SELECT p from " + Marca.class.getName() + " p"
				+ " where nomMarca = '" + nomMarca + "'";
		TypedQuery<Marca> query = entityManager.createQuery(q, Marca.class);

		List<Marca> result = query.getResultList();
		if (result == null) {
			result = new ArrayList<Marca>();
		}
		return result;
	}

}
