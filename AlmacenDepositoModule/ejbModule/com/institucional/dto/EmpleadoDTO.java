package com.institucional.dto;

import java.io.Serializable;

public class EmpleadoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer codPersona;
	
	private String nomPersona;
	
	private String cuil;
	
	private String telefono;
	
	private String celular;
	
	private String domicilio;
	
	private String legajo;
	
	private String conResPatriminial;

	private String uOrganica;
	
	private Integer nroUOrganica;
	
	private String mail;
	
	private Integer tipoPersona = 1 ;

	public Integer getCodPersona() {
		return codPersona;
	}

	public void setCodPersona(Integer codPersona) {
		this.codPersona = codPersona;
	}

	public String getNomPersona() {
		return nomPersona;
	}

	public void setNomPersona(String nomPersona) {
		this.nomPersona = nomPersona;
	}

	
	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getuOrganica() {
		return uOrganica;
	}

	public void setuOrganica(String uOrganica) {
		this.uOrganica = uOrganica;
	}

	public String getConResPatriminial() {
		return conResPatriminial;
	}

	public void setConResPatriminial(String conResPatriminial) {
		this.conResPatriminial = conResPatriminial;
	}

	public Integer getNroUOrganica() {
		return nroUOrganica;
	}

	public void setNroUOrganica(Integer nroUOrganica) {
		this.nroUOrganica = nroUOrganica;
	}

	public Integer getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(Integer tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
	
	
	
	 

}
