package com.movimiento.repository;

import java.util.ArrayList;
import java.util.List;

import com.application.repository.Repositorio;
import com.movimiento.entities.TipoMovOrigen;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class TipoMORepository
 */
@Stateless
@LocalBean
public class TipoMORepository implements Repositorio <Integer, TipoMovOrigen> {

	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(TipoMovOrigen newOne) {
		entityManager.persist(newOne);
	}

	@Override
	public void remove(TipoMovOrigen toDelete) {
		entityManager.remove(toDelete);
	}

	public TipoMovOrigen get(Integer id) {
		return entityManager.find(TipoMovOrigen.class, id);
	}

	@Override
	public List<TipoMovOrigen> getAll() {
		String q = "SELECT p from " + TipoMovOrigen.class.getName() + " p ";
		TypedQuery<TipoMovOrigen> query = entityManager.createQuery(q, TipoMovOrigen.class);
		
		List<TipoMovOrigen> result = query.getResultList();
		if(result == null) {
			result = new ArrayList<TipoMovOrigen>();
		}
		return result;
	}

	@Override
	public long size() {
		String q = "SELECT count(p) from " + TipoMovOrigen.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}

	public List<TipoMovOrigen> getNomTMovOrigen(String nomTMovOrigen) {
		// TODO Auto-generated method stub
		String q = "SELECT p from " + TipoMovOrigen.class.getName() + " p" + " where nomTMovOrigen = '" + nomTMovOrigen + "'";
		TypedQuery<TipoMovOrigen> query =  entityManager.createQuery(q, TipoMovOrigen.class);
		
		List<TipoMovOrigen> result = query.getResultList();
		if (result == null){
			result = new ArrayList<TipoMovOrigen>();
		}
		return result;
	}

}
