package com.general;

import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.movimiento.dto.MovSalidaDTO;
import com.movimiento.module.MovSalidaServiceRemote;


@ManagedBean
@RequestScoped
public class MovSalidaForm {
	@EJB
	private MovSalidaServiceRemote moService;
	
	public Collection<MovSalidaDTO> getMovSalidas() {
		return moService.listAll();
	}


}
