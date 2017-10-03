package com.general;

import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.institucional.dto.PParcialDTO;
import com.institucional.module.PParcialServiceRemote;


@ManagedBean
@RequestScoped
public class IncisoForm {
	
	@EJB
	private PParcialServiceRemote ppService;

	public Collection<PParcialDTO> getPParcial() {
		return ppService.listAll();
	}


}
