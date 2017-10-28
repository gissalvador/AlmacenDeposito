package com.general;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.articulo.dto.ArticuloDTO;
import com.movimiento.dto.MovSalidaDTO;
import com.movimiento.module.MovSalidaServiceRemote;


@ManagedBean
@RequestScoped
public class MovSalidaForm {
	@EJB
	private MovSalidaServiceRemote moService;
	
	private List<ArticuloDTO>  filteredMoss;
	
	
	public List<ArticuloDTO> getFilteredMoss() {
		return filteredMoss;
	}

	public void setFilteredMoss(List<ArticuloDTO> filteredMoss) {
		this.filteredMoss = filteredMoss;
	}


	public Collection<MovSalidaDTO> getMovSalidas() {
		return moService.listAll();
	}


}
