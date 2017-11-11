package com.general;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.articulo.dto.ArticuloDTO;
import com.institucional.dto.ActividadDTO;
import com.institucional.module.ActividadServiceRemote;



@ManagedBean
@RequestScoped
public class ActividadForm {
	@EJB
	private ActividadServiceRemote aService;
	
	private List<ActividadDTO>  filteredActs;
		
	public List<ActividadDTO> getFilteredActs() {
		return filteredActs;
	}

	public void setFilteredActs(List<ActividadDTO> filteredActs) {
		this.filteredActs = filteredActs;
	}

	public Collection<ActividadDTO> getActividades() {
		return aService.listAll();
	}

}
