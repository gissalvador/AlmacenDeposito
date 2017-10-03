package com.articulo.modules;

import java.util.Collection;

import javax.ejb.Remote;

import com.articulo.dto.MaterialDTO;

@Remote
public interface MaterialServiceRemote {

	public abstract MaterialDTO findById(Integer materialId);
	
	public abstract void addMaterial(MaterialDTO material);

	Collection<MaterialDTO> listAll();

}
