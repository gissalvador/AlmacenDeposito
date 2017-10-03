package com.institucional.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IncisoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int inciso;
	private int nroInciso;
	private String nomInciso;
	
	private List<PPrincipalDTO> listPPrincipalDTO;

	public int getInciso() {
		return inciso;
	}

	public void setInciso(int inciso) {
		this.inciso = inciso;
	}

	public int getNroInciso() {
		return nroInciso;
	}

	public void setNroInciso(int nroInciso) {
		this.nroInciso = nroInciso;
	}

	public String getNomInciso() {
		return nomInciso;
	}

	public void setNomInciso(String nomInciso) {
		this.nomInciso = nomInciso;
	}


	public List<PPrincipalDTO> getListPPrincipalDTO() {
		return listPPrincipalDTO;
	}

	public void setListPPrincipalDTO(List<PPrincipalDTO> listPPrincipalDTO) {
		this.listPPrincipalDTO = listPPrincipalDTO;
	}

	public void addDetalle(PPrincipalDTO pprincipalDTO){
		
		if (listPPrincipalDTO == null) {
			listPPrincipalDTO = new ArrayList<PPrincipalDTO>();
		}
		
			
		listPPrincipalDTO.add(pprincipalDTO);
			
	}
	
	
	
	

}
