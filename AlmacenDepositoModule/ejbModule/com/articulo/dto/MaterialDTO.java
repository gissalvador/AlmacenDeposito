package com.articulo.dto;

import java.io.Serializable;


public class MaterialDTO implements Serializable{
	
	/**
	 * 
	 **/
	
	
	private static final long serialVersionUID = 1L;
	
	private Integer codMaterial;
	private String nomMaterial;
	private String desMaterial;
	private String tipoMaterial;
	
	
	public Integer getCodMaterial() {
		return codMaterial;
	}
	public void setCodMaterial(Integer codMaterial) {
		this.codMaterial = codMaterial;
	}
	public String getNomMaterial() {
		return nomMaterial;
	}
	public void setNomMaterial(String nomMaterial) {
		this.nomMaterial = nomMaterial;
	}
	
	public String getDesMaterial() {
		return desMaterial;
	}
	public void setDesMaterial(String desMaterial) {
		this.desMaterial = desMaterial;
	}
	public String getTipoMaterial() {
		return tipoMaterial;
	}
	public void setTipoMaterial(String tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}
	
	
	
	

}
