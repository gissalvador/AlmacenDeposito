package com.general;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.articulo.dto.ArticuloDTO;
import com.institucional.dto.ProveedorDTO;
import com.institucional.module.ProveedorServiceRemote;



@ManagedBean
@RequestScoped
public class ProveedorForm {
	
	@EJB
	private ProveedorServiceRemote pService;
	
	private List<ArticuloDTO>  filteredPros;
	
	
	public List<ArticuloDTO> getFilteredPros() {
		return filteredPros;
	}


	public void setFilteredPros(List<ArticuloDTO> filteredPros) {
		this.filteredPros = filteredPros;
	}


	public Collection<ProveedorDTO> getProveedores() {
		return pService.listAll();
	}
	

}
