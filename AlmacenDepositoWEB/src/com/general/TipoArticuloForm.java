package com.general;

import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.articulo.dto.TipoArticuloDTO;
import com.articulo.modules.TipoArticuloServiceRemote;


@ManagedBean
@RequestScoped
public class TipoArticuloForm {
	
	@EJB
	private TipoArticuloServiceRemote taService;

	public Collection<TipoArticuloDTO> getTipoArticulos() {
		return taService.listAll();
	}

}
