package com.institucional.dto;

import java.io.Serializable;

public class CentroDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer codCentro;

	private String nomCentro;

	public Integer getCodCentro() {
		return codCentro;
	}

	public void setCodCentro(Integer codCentro) {
		this.codCentro = codCentro;
	}

	public String getNomCentro() {
		return nomCentro;
	}

	public void setNomCentro(String nomCentro) {
		this.nomCentro = nomCentro;
	}
	
	

}
