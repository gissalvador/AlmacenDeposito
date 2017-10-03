package com.general;

import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.institucional.dto.TipoPersonaDTO;
import com.institucional.module.TipoPersonaServiceRemote;


@ManagedBean
@RequestScoped
public class TipoPersonaForm {
	
	@EJB
	private TipoPersonaServiceRemote tpService;

	public Collection<TipoPersonaDTO> getTipoPersonas() {
		return tpService.listAll();
	}
	

}
