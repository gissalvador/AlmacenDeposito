package com.institucional.dto;

import java.io.Serializable;


public class ActividadDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private Integer codActividad;
	
	private Integer nroActividad;
	
	private String redProgramatica;
	
	private String nomActividad;
	
	private String responsable;
		
	private String subresponsable;
	
	private String nroRes;
	
	private String nroSubRes;
	
	

	public Integer getCodActividad() {
		return codActividad;
	}

	public void setCodActividad(Integer codActividad) {
		this.codActividad = codActividad;
	}

	public Integer getNroActividad() {
		return nroActividad;
	}

	public void setNroActividad(Integer nroActividad) {
		this.nroActividad = nroActividad;
	}

	public String getRedProgramatica() {
		return redProgramatica;
	}

	public void setRedProgramatica(String redProgramatica) {
		this.redProgramatica = redProgramatica;
	}

	public String getNomActividad() {
		return nomActividad;
	}

	public void setNomActividad(String nomActividad) {
		this.nomActividad = nomActividad;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getSubresponsable() {
		return subresponsable;
	}

	public void setSubresponsable(String subresponsable) {
		this.subresponsable = subresponsable;
	}

	public String getNroRes() {
		return nroRes;
	}

	public void setNroRes(String nroRes) {
		this.nroRes = nroRes;
	}

	public String getNroSubRes() {
		return nroSubRes;
	}

	public void setNroSubRes(String nroSubRes) {
		this.nroSubRes = nroSubRes;
	}


	
	
	
	
	
}
