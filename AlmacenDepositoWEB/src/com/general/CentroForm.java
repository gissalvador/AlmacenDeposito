package com.general;


import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.institucional.dto.CentroDTO;
import com.institucional.module.CentroServiceRemote;


/**
 * Formulario que muestra la grilla de Centros.
 * 
 * @author Gisella
 * 
 */
@ManagedBean (name = "centroForm")
@RequestScoped
public class CentroForm {
	
	@EJB
	private CentroServiceRemote centroService;

	public Collection<CentroDTO> getCentros() {
		return centroService.listAll();
	}

}
