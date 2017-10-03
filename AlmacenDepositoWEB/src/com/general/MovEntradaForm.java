package com.general;


import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.movimiento.module.MovEntradaServiceRemote;
import com.movimiento.dto.MovEntradaDTO;



@ManagedBean
@RequestScoped
public class MovEntradaForm {
	@EJB
	private MovEntradaServiceRemote meService;
	
	public Collection<MovEntradaDTO> getMovEntradas() {
		return meService.listAll();
	}

	
	
	
}
