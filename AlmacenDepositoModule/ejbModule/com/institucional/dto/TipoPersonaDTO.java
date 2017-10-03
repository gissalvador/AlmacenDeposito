package com.institucional.dto;

import java.io.Serializable;

public class TipoPersonaDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer codTipoPersona;
	
	private String nomTipoPersona;

	public Integer getCodTipoPersona() {
		return codTipoPersona;
	}

	public void setCodTipoPersona(Integer codTipoPersona) {
		this.codTipoPersona = codTipoPersona;
	}

	public String getNomTipoPersona() {
		return nomTipoPersona;
	}

	public void setNomTipoPersona(String nomTipoPersona) {
		this.nomTipoPersona = nomTipoPersona;
	}
	
	
	

}
