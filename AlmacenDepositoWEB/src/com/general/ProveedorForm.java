package com.general;

import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.institucional.dto.ProveedorDTO;
import com.institucional.module.ProveedorServiceRemote;



@ManagedBean
@RequestScoped
public class ProveedorForm {
	
	@EJB
	private ProveedorServiceRemote pService;

	public Collection<ProveedorDTO> getProveedores() {
		return pService.listAll();
	}
	

}
