package com.general;

import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.articulo.dto.ArticuloDTO;
import com.articulo.modules.ArticuloServicesNewRemote;




@ManagedBean
@RequestScoped
public class ArticuloForm {
	
	@EJB
	private ArticuloServicesNewRemote aService;

	public Collection<ArticuloDTO> getArticulos() {
		return aService.listAll();
	}
	

}
