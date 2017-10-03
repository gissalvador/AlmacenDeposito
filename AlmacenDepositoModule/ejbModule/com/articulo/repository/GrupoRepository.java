package com.articulo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.application.repository.Repositorio;
import com.articulo.entities.Grupo;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class GrupoRepository implements Repositorio<Integer, Grupo> {

	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;

	@Override
	public void add(Grupo newOne) {
		entityManager.persist(newOne);
	}

	@Override
	public void remove(Grupo toDelete) {
		entityManager.remove(toDelete);
	}

	@Override
	public Grupo get(Integer id) {
		return entityManager.find(Grupo.class, id);
	}

	@Override
	public List<Grupo> getAll() {
		String q = "SELECT p from " + Grupo.class.getName() + " p";
		TypedQuery<Grupo> query = entityManager.createQuery(q, Grupo.class);

		List<Grupo> result = query.getResultList();
		if (result == null) {
			result = new ArrayList<Grupo>();
		}
		return result;
	}

	@Override
	public long size() {
		String q = "SELECT count(p) from " + Grupo.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}

	public List<Grupo> getNomGrupo(String nomGrupo) {
		// TODO Auto-generated method stub
		String q = "SELECT p from " + Grupo.class.getName() + " p"
				+ " where nomGrupo = '" + nomGrupo + "'";
		TypedQuery<Grupo> query = entityManager.createQuery(q, Grupo.class);

		List<Grupo> result = query.getResultList();
		if (result == null) {
			result = new ArrayList<Grupo>();
		}
		return result;
	}
}
