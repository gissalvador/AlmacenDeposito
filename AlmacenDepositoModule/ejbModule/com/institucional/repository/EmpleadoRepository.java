package com.institucional.repository;

import java.util.ArrayList;
import java.util.List;

import com.application.repository.Repositorio;
import com.institucional.entities.Empleado;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class EmpleadoRepository
 */
@Stateless
@LocalBean
public class EmpleadoRepository implements Repositorio<Integer, Empleado> {

	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(Empleado newOne) {
		entityManager.persist(newOne);
	}

	@Override
	public void remove(Empleado toDelete) {
		entityManager.remove(toDelete);
	}

	public Empleado get(Integer id) {
		return entityManager.find(Empleado.class, id);
	}

	@Override
	public List<Empleado> getAll() {
		String q = "SELECT p from " + Empleado.class.getName() + " p ";
		TypedQuery<Empleado> query = entityManager.createQuery(q, Empleado.class);
		
		List<Empleado> result = query.getResultList();
		if(result == null) {
			result = new ArrayList<Empleado>();
		}
		return result;
	}

	@Override
	public long size() {
		String q = "SELECT count(p) from " + Empleado.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}
	
	public List<Empleado> getEmpleado(String legajo) {

		String q = "SELECT p from " + Empleado.class.getName() + " p"
				+ " where legajo = " +"'"+legajo+"'";
		TypedQuery<Empleado> query = entityManager.createQuery(q,
				Empleado.class);

		List<Empleado> result = query.getResultList();
		if (result == null) {
			result = new ArrayList<Empleado>();
		}
		return result;
	}

}
