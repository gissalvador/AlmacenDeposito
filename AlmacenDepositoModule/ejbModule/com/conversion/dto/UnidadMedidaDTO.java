package com.conversion.dto;

import java.io.Serializable;

public class UnidadMedidaDTO implements Serializable{
	
	/**
	 * 
	 **/
		
	private static final long serialVersionUID = 1L;
	
	
	private Integer unidadMedida;
	
	private String nombre;
	
	private String simbolo;
	
	private Integer codTUM;
	
	private String nomTipoUM;

	public Integer getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(Integer unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public Integer getCodTUM() {
		return codTUM;
	}

	public void setCodTUM(Integer codTUM) {
		this.codTUM = codTUM;
	}

	public String getNomTipoUM() {
		return nomTipoUM;
	}

	public void setNomTipoUM(String nomTipoUM) {
		this.nomTipoUM = nomTipoUM;
	}
	
	

}
