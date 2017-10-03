package com.institucional.repository;

import java.util.ArrayList;
import java.util.List;

import com.application.repository.Repositorio;
import com.institucional.entities.Proveedor;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class ProveedorRepository
 */
@Stateless
@LocalBean
public class ProveedorRepository implements Repositorio <Integer, Proveedor> {

	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(Proveedor newOne) {
		entityManager.persist(newOne);
	}

	@Override
	public void remove(Proveedor toDelete) {
		entityManager.remove(toDelete);
	}

	public Proveedor get(Integer id) {
		return entityManager.find(Proveedor.class, id);
	}

	@Override
	public List<Proveedor> getAll() {
		String q = "SELECT p from " + Proveedor.class.getName() + " p ";
		TypedQuery<Proveedor> query = entityManager.createQuery(q, Proveedor.class);
		
		List<Proveedor> result = query.getResultList();
		if(result == null) {
			result = new ArrayList<Proveedor>();
		}
		return result;
	}

	@Override
	public long size() {
		String q = "SELECT count(p) from " + Proveedor.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}
	
	public List<Proveedor> getProveedor(String cuit) {

		String q = "SELECT p from " + Proveedor.class.getName() + " p"
				+ " where cuit = " +"'"+cuit+"'";
		TypedQuery<Proveedor> query = entityManager.createQuery(q,
				Proveedor.class);

		List<Proveedor> result = query.getResultList();
		if (result == null) {
			result = new ArrayList<Proveedor>();
		}
		return result;
	}

	public List<Proveedor> getCUIT(String cuit) {
		// TODO Auto-generated method stub
		String q = "SELECT p from " + Proveedor.class.getName() + " p"
				+ " where cuit = '" + cuit + "'";
		TypedQuery<Proveedor> query = entityManager.createQuery(q, Proveedor.class);

		List<Proveedor> result = query.getResultList();
		if (result == null) {
			result = new ArrayList<Proveedor>();
		}
		return result;
	}

}
