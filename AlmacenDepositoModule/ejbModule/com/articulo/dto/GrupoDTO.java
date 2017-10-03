package com.articulo.dto;

import java.io.Serializable;


public class GrupoDTO implements Serializable{
	
	/**
	 * 
	 **/
	
	
	private static final long serialVersionUID = 1L;
	
	private Integer codGrupo;
	private String nomGrupo;
	private String desGrupo;
	
	public Integer getCodGrupo() {
		return codGrupo;
	}
	public void setCodGrupo(Integer codGrupo) {
		this.codGrupo = codGrupo;
	}
	public String getNomGrupo() {
		return nomGrupo;
	}
	public void setNomGrupo(String nomGrupo) {
		this.nomGrupo = nomGrupo;
	}
	public String getDesGrupo() {
		return desGrupo;
	}
	public void setDesGrupo(String desGrupo) {
		this.desGrupo = desGrupo;
	}
	
	
	
	
	

}
