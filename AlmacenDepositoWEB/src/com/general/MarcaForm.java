package com.general;

import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.movimiento.dto.MarcaDTO;
import com.movimiento.module.MarcaServiceRemote;

@ManagedBean
@RequestScoped
public class MarcaForm {
	
	@EJB
	private MarcaServiceRemote mService;

	public Collection<MarcaDTO> getMarcas() {
		return mService.listAll();
	}
	
}
