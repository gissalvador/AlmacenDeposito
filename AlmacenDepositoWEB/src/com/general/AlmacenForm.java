package com.general;

import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.institucional.dto.AlmacenDTO;
import com.institucional.module.AlmacenServiceRemote;


/**
 * Formulario que muestra la grilla de Almacenes.
 * 
 * @author Gisella
 * 
 */
@ManagedBean
@RequestScoped
public class AlmacenForm {
	
	@EJB
	private AlmacenServiceRemote almacenService;

	public Collection<AlmacenDTO> getAlmacenes() {
		return almacenService.listAll();
	}

}
