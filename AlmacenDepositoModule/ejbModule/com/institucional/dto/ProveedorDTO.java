package com.institucional.dto;

import java.io.Serializable;

public class ProveedorDTO implements Serializable{
	
	/**
	 * 
	 **/
	
	
	private static final long serialVersionUID = 1L;
	
	private Integer codPersona;
	
	private String razonSocial;
	
	private String cuit;
	
	private String mail;
	
	private String telefono;
	
	private String celular;
	
	private String domicilio;
	
	private String alias;
	
	private String nroProveedor;
	
	private Integer tipoPersona = 2 ;

	public Integer getCodPersona() {
		return codPersona;
	}

	public void setCodPersona(Integer codPersona) {
		this.codPersona = codPersona;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}


	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}


	public String getNroProveedor() {
		return nroProveedor;
	}

	public void setNroProveedor(String nroProveedor) {
		this.nroProveedor = nroProveedor;
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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
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
