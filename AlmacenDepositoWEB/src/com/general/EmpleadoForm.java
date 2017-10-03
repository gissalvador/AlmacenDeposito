package com.general;

import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.institucional.dto.EmpleadoDTO;
import com.institucional.module.EmpleadoServiceRemote;


@ManagedBean
@RequestScoped
public class EmpleadoForm {
	
	@EJB
	private EmpleadoServiceRemote eService;

	public Collection<EmpleadoDTO> getEmpleados() {
		return eService.listAll();
	}
	

}
