package com.movimiento.repository;

import java.util.ArrayList;
import java.util.List;

import com.application.repository.Repositorio;
import com.movimiento.entities.Modelo;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class ModeloRepository
 */
@Stateless
@LocalBean
public class ModeloRepository implements Repositorio <Integer, Modelo> {

	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(Modelo newOne) {
		entityManager.persist(newOne);
	}

	@Override
	public void remove(Modelo toDelete) {
		entityManager.remove(toDelete);
	}

	public Modelo get(Integer id) {
		return entityManager.find(Modelo.class, id);
	}

	@Override
	public List<Modelo> getAll() {
		String q = "SELECT p from " + Modelo.class.getName() + " p ";
		TypedQuery<Modelo> query = entityManager.createQuery(q, Modelo.class);
		
		List<Modelo> result = query.getResultList();
		if(result == null) {
			result = new ArrayList<Modelo>();
		}
		return result;
	}

	@Override
	public long size() {
		String q = "SELECT count(p) from " + Modelo.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}
	
	
	public List<Modelo> getAllModelo(Integer marcaId) {
		String q = "SELECT p from " + Modelo.class.getName() + " p " +  "where marca = " + marcaId ;
		TypedQuery<Modelo> query = entityManager.createQuery(q, Modelo.class);
		
		List<Modelo> result = query.getResultList();
		if(result == null) {
			result = new ArrayList<Modelo>();
		}
		return result;
	}

	public List<Modelo> getNomModelo(String nomModelo, Integer marcaId) {
		// TODO Auto-generated method stub
		String q = "SELECT p from " + Modelo.class.getName() + " p"
				+ " where nomModelo = '" + nomModelo + "' and marcaId = "
				+ marcaId;
		TypedQuery<Modelo> query = entityManager.createQuery(q, Modelo.class);

		List<Modelo> result = query.getResultList();
		if (result == null) {
			result = new ArrayList<Modelo>();
		}
		return result;
	}
	
	

}
