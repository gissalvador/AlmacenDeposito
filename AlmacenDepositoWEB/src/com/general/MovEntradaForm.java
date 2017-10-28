package com.general;


import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.movimiento.module.MovEntradaServiceRemote;
import com.articulo.dto.ArticuloDTO;
import com.movimiento.dto.MovEntradaDTO;



@ManagedBean
@RequestScoped
public class MovEntradaForm {
	@EJB
	private MovEntradaServiceRemote meService;
	
	private List<ArticuloDTO>  filteredMoes;
	
		
	public List<ArticuloDTO> getFilteredMoes() {
		return filteredMoes;
	}


	public void setFilteredMoes(List<ArticuloDTO> filteredMoes) {
		this.filteredMoes = filteredMoes;
	}


	public Collection<MovEntradaDTO> getMovEntradas() {
		return meService.listAll();
	}

	
	
	
}
