package com.articulo.dto;

import java.io.Serializable;

public class TipoArticuloDTO implements Serializable{
	
	/**
	 * 
	 **/
	
	
	private static final long serialVersionUID = 1L;
	
	private Integer codTipoArticulo;
	private String descArticulo;
	private String nomArticulo;
	
	public Integer getCodTipoArticulo() {
		return codTipoArticulo;
	}
	public void setCodTipoArticulo(Integer codTipoArticulo) {
		this.codTipoArticulo = codTipoArticulo;
	}
	public String getDescArticulo() {
		return descArticulo;
	}
	public void setDescArticulo(String descArticulo) {
		this.descArticulo = descArticulo;
	}
	public String getNomArticulo() {
		return nomArticulo;
	}
	public void setNomArticulo(String nomArticulo) {
		this.nomArticulo = nomArticulo;
	}
	
	

}
