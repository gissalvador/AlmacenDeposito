package com.movimiento.dto;

import java.io.Serializable;



public class TipoMovOrigenDTO implements Serializable{
	
	/**
	 * 
	 **/
	
	
	private static final long serialVersionUID = 1L;
	
	private Integer codTMovOrigen;
	private String nomTMovOrigen;
	private Integer nroTipoPersona;
	private String tipoPersona;
	public Integer getCodTMovOrigen() {
		return codTMovOrigen;
	}
	public void setCodTMovOrigen(Integer codTMovOrigen) {
		this.codTMovOrigen = codTMovOrigen;
	}
	public String getNomTMovOrigen() {
		return nomTMovOrigen;
	}
	public void setNomTMovOrigen(String nomTMovOrigen) {
		this.nomTMovOrigen = nomTMovOrigen;
	}
	public Integer getNroTipoPersona() {
		return nroTipoPersona;
	}
	public void setNroTipoPersona(Integer nroTipoPersona) {
		this.nroTipoPersona = nroTipoPersona;
	}
	public String getTipoPersona() {
		return tipoPersona;
	}
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	
	
	
	

}
