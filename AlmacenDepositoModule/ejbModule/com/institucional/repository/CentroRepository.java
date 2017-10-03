package com.institucional.repository;

import java.util.ArrayList;
import java.util.List;

import com.application.repository.Repositorio;
import com.institucional.entities.Centro;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class CentroRepository
 */
@Stateless
@LocalBean
public class CentroRepository implements Repositorio<Integer, Centro> {
	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(Centro newOne) {
		entityManager.persist(newOne);
	}

	@Override
	public void remove(Centro toDelete) {
		entityManager.remove(toDelete);
	}

	public Centro get(Integer id) {
		return entityManager.find(Centro.class, id);
	}

	@Override
	public List<Centro> getAll() {
		String q = "SELECT p from " + Centro.class.getName() + " p ";
		TypedQuery<Centro> query = entityManager.createQuery(q, Centro.class);
		
		List<Centro> result = query.getResultList();
		if(result == null) {
			result = new ArrayList<Centro>();
		}
		return result;
	}

	@Override
	public long size() {
		String q = "SELECT count(p) from " + Centro.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}
	
	public List<Centro> getNomCentro(String nombreCentro){
			
			String q = "SELECT p from " + Centro.class.getName() + " p" + " where nomCentro = '" + nombreCentro + "'";
			TypedQuery<Centro> query =  entityManager.createQuery(q, Centro.class);
			
			List<Centro> result = query.getResultList();
			if (result == null){
				result = new ArrayList<Centro>();
			}
			return result;
		}

}
