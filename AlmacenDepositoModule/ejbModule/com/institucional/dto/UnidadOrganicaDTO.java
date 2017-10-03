package com.institucional.dto;

import java.io.Serializable;


public class UnidadOrganicaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private Integer codUnidadOrganica;
	
	private String nomUnidaOrganica;
	
	private String nroDependencia;
	
	private Integer depende;
	
	private String nroDepende;
	
	private String nomDepende;
	
	private Integer codPersona;
	
	private String nomPersona;
	
	private String legajo;

	public Integer getCodUnidadOrganica() {
		return codUnidadOrganica;
	}

	public void setCodUnidadOrganica(Integer codUnidadOrganica) {
		this.codUnidadOrganica = codUnidadOrganica;
	}
		
	public String getNroDependencia() {
		return nroDependencia;
	}

	public void setNroDependencia(String nroDependencia) {
		this.nroDependencia = nroDependencia;
	}

	public String getNomUnidaOrganica() {
		return nomUnidaOrganica;
	}

	public void setNomUnidaOrganica(String nomUnidaOrganica) {
		this.nomUnidaOrganica = nomUnidaOrganica;
	}

	public Integer getDepende() {
		return depende;
	}

	public void setDepende(Integer depende) {
		this.depende = depende;
	}

	public Integer getCodPersona() {
		return codPersona;
	}

	public void setCodPersona(Integer codPersona) {
		this.codPersona = codPersona;
	}

	public String getNomDepende() {
		return nomDepende;
	}

	public void setNomDepende(String nomDepende) {
		this.nomDepende = nomDepende;
	}

	public String getNomPersona() {
		return nomPersona;
	}

	public void setNomPersona(String nomPersona) {
		this.nomPersona = nomPersona;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	public String getNroDepende() {
		return nroDepende;
	}

	public void setNroDepende(String nroDepende) {
		this.nroDepende = nroDepende;
	}


	
	
	
	
	

}
