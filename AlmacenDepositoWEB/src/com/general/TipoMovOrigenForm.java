package com.general;

import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.movimiento.dto.TipoMovOrigenDTO;
import com.movimiento.module.TipoMovOrigenServiceRemote;


@ManagedBean
@RequestScoped
public class TipoMovOrigenForm {
	
	@EJB
	private TipoMovOrigenServiceRemote tmoService;

	public Collection<TipoMovOrigenDTO> getTipoMovOrigenes() {
		return tmoService.listAll();
	}

}
