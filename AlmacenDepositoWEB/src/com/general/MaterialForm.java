package com.general;


import java.util.Collection;
import java.util.List;

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

	private List<MaterialDTO>  filteredMats;
	
	public Collection<MaterialDTO> getMaterials() {
		return maService.listAll();
	}

	public List<MaterialDTO> getFilteredMats() {
		return filteredMats;
	}

	public void setFilteredMats(List<MaterialDTO> filteredMats) {
		this.filteredMats = filteredMats;
	}
	
	

}