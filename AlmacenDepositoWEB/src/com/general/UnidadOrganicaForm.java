package com.general;

import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.institucional.dto.UnidadOrganicaDTO;
import com.institucional.module.UnidadOrganicaServiceRemote;

@ManagedBean
@RequestScoped
public class UnidadOrganicaForm {
	
	@EJB
	private UnidadOrganicaServiceRemote uoService;

	public Collection<UnidadOrganicaDTO> getUnidadOrganicas() {
		return uoService.listAll();
	}
	

}
