package com.general;

import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.conversion.dto.UnidadMedidaDTO;
import com.conversion.module.UnidadMedidaServiceRemote;


@ManagedBean
@RequestScoped
public class UnidadMedidaForm {
	
	@EJB
	private UnidadMedidaServiceRemote umService;

	public Collection<UnidadMedidaDTO> getUnidadMedidas() {
		return umService.listAll();
	}

}
