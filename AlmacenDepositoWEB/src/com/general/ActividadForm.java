package com.general;

import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.institucional.dto.ActividadDTO;
import com.institucional.module.ActividadServiceRemote;



@ManagedBean
@RequestScoped
public class ActividadForm {
	@EJB
	private ActividadServiceRemote aService;

	public Collection<ActividadDTO> getActividades() {
		return aService.listAll();
	}

}
