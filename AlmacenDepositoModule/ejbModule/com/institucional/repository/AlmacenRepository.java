package com.institucional.repository;

import java.util.ArrayList;
import java.util.List;

import com.application.repository.Repositorio;
import com.institucional.entities.Almacen;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class AlmacenRepository
 */
@Stateless
@LocalBean
public class AlmacenRepository implements Repositorio<Integer, Almacen> {

	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(Almacen newOne) {
		entityManager.persist(newOne);
	}

	@Override
	public void remove(Almacen toDelete) {
		entityManager.remove(toDelete);
	}

	public Almacen get(Integer id) {

		System.out.println(id);
		return entityManager.find(Almacen.class, id);
	}

	@Override
	public List<Almacen> getAll() {
		String q = "SELECT p from " + Almacen.class.getName() + " p ";
		TypedQuery<Almacen> query = entityManager.createQuery(q, Almacen.class);

		List<Almacen> result = query.getResultList();
		if (result == null) {
			result = new ArrayList<Almacen>();
		}
		return result;
	}

	@Override
	public long size() {
		String q = "SELECT count(p) from " + Almacen.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}

	public List<Almacen> getNomAlmacen(String nomAlmacen, Integer codCentro) {
		// TODO Auto-generated method stub

		String q = "SELECT p from " + Almacen.class.getName() + " p"
				+ " where nomAlmacen = '" + nomAlmacen + "' and centroId = "
				+ codCentro;
		TypedQuery<Almacen> query = entityManager.createQuery(q, Almacen.class);

		List<Almacen> result = query.getResultList();
		if (result == null) {
			result = new ArrayList<Almacen>();
		}
		return result;
	}

}
