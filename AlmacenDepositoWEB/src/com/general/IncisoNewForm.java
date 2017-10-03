package com.general;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.institucional.dto.IncisoDTO;
import com.institucional.module.IncisioServiceRemote;



@ManagedBean
@RequestScoped
public class IncisoNewForm {
	
	@EJB
	private IncisioServiceRemote iService;
	
	private Collection<IncisoDTO> incisos;

	public Collection<IncisoDTO> getInciso() {
		return iService.listAll();
	}
	
	@PostConstruct
	private void initialize() {
		
		incisos = new ArrayList<IncisoDTO>();
		
		incisos = this.getInciso();
		
		}
	
	
	public Collection<IncisoDTO> getIncisos() {
		return incisos;
	}

}
