package com.articulo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.application.repository.Repositorio;
import com.articulo.entities.Material;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
@Stateless
@LocalBean
public class MaterialRepository implements Repositorio<Integer, Material> {
	@PersistenceContext(unitName = "AlmacenDepositoDS")
	private EntityManager entityManager;
	
	@Override
	public void add(Material newOne){
		entityManager.persist(newOne);
	}
	@Override
	public void remove(Material toDelete){
		entityManager.remove(toDelete);
	}


	@Override
	public Material get(Integer id) {
		return entityManager.find(Material.class, id);
	}

	@Override
	public List<Material> getAll() {
		String q = "SELECT p from " + Material.class.getName() + " p";
		TypedQuery<Material> query = entityManager.createQuery(q, Material.class);
		
		List<Material> result = query.getResultList();
		if (result == null){
			result = new ArrayList<Material>();
		}
		return result;
	}

	
	@Override
	public long size() {
		String q = "SELECT count(p) from " + Material.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}
	public List<Material> getNomMaterial(String nomMaterial) {
		// TODO Auto-generated method stub
		String q = "SELECT p from " + Material.class.getName() + " p"
				+ " where nomMaterial = '" + nomMaterial + "'";
		TypedQuery<Material> query = entityManager.createQuery(q, Material.class);

		List<Material> result = query.getResultList();
		if (result == null) {
			result = new ArrayList<Material>();
		}
		return result;
	}
}
