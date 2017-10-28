package com.general;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.articulo.dto.ArticuloDTO;
import com.institucional.dto.EmpleadoDTO;
import com.institucional.module.EmpleadoServiceRemote;


@ManagedBean
@RequestScoped
public class EmpleadoForm {
	
	@EJB
	private EmpleadoServiceRemote eService;
	
	private List<ArticuloDTO>  filteredEmps;
	
	
	public List<ArticuloDTO> getFilteredEmps() {
		return filteredEmps;
	}

	public void setFilteredEmps(List<ArticuloDTO> filteredEmps) {
		this.filteredEmps = filteredEmps;
	}

	public Collection<EmpleadoDTO> getEmpleados() {
		return eService.listAll();
	}
	

}
