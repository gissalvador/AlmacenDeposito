package com.general;


import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.articulo.dto.MaterialDTO;
import com.articulo.modules.MaterialServiceRemote;





@ManagedBean
@RequestScoped
public class MaterialForm {
	@EJB
	private MaterialServiceRemote maService;

	public Collection<MaterialDTO> getMaterials() {
		return maService.listAll();
	}

}