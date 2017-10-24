package com.seguridad.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.application.repository.Repositorio;
import com.seguridad.entities.CambioClave;

/**
 * Session Bean implementation class CambioClaveRepository
 */
@Stateless
@LocalBean
public class CambioClaveRepository implements Repositorio<Integer, CambioClave> {

    	@PersistenceContext(unitName = "AlmacenDepositoDS")
    	private EntityManager entityManager;

    	@Override
    	public void add(CambioClave newOne) {
    		entityManager.persist(newOne);
    	}

    	@Override
    	public void remove(CambioClave toDelete) {
    		entityManager.remove(toDelete);
    	}

    	@Override
    	public CambioClave get(Integer id) {
    		return entityManager.find(CambioClave.class, id);
    	}

    	@Override
    	public List<CambioClave> getAll() {
    		String q = "SELECT p from " + CambioClave.class.getName() + " p";
    		TypedQuery<CambioClave> query = entityManager.createQuery(q, CambioClave.class);

    		List<CambioClave> result = query.getResultList();
    		if (result == null) {
    			result = new ArrayList<CambioClave>();
    		}
    		return result;
    	}
    	
    	
    	public List<CambioClave> getcodigo(String usuario, String codR) {
    		String q = "SELECT p from " + CambioClave.class.getName() + " p where usuario = '" + usuario + "' and codRecuperar = '" + codR +  "' ORDER BY finVigencia DESC";
    		TypedQuery<CambioClave> query = entityManager.createQuery(q, CambioClave.class);

    		List<CambioClave> result = query.getResultList();
    		if (result == null) {
    			result = new ArrayList<CambioClave>();
    		}
    		return result;
    	}

    	@Override
    	public long size() {
    		String q = "SELECT count(p) from " + CambioClave.class.getName() + " p";
    		return (Long) entityManager.createQuery(q).getSingleResult();
    	}

    	public List<CambioClave> getNomCambioClave(String nomCambioClave) {
    		// TODO Auto-generated method stub
    		String q = "SELECT p from " + CambioClave.class.getName() + " p"
    				+ " where nomCambioClave = '" + nomCambioClave + "'";
    		TypedQuery<CambioClave> query = entityManager.createQuery(q, CambioClave.class);

    		List<CambioClave> result = query.getResultList();
    		if (result == null) {
    			result = new ArrayList<CambioClave>();
    		}
    		return result;
    	}

}
