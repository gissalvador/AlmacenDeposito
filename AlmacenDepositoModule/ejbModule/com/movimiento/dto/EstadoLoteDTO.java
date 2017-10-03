package com.movimiento.dto;

import java.io.Serializable;

public class EstadoLoteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int codEstadoLote;
	
	private String nomEstadoLote;
	
	private String desEstadoLote;

	public int getCodEstadoLote() {
		return codEstadoLote;
	}

	public void setCodEstadoLote(int codEstadoLote) {
		this.codEstadoLote = codEstadoLote;
	}

	public String getNomEstadoLote() {
		return nomEstadoLote;
	}

	public void setNomEstadoLote(String nomEstadoLote) {
		this.nomEstadoLote = nomEstadoLote;
	}

	public String getDesEstadoLote() {
		return desEstadoLote;
	}

	public void setDesEstadoLote(String desEstadoLote) {
		this.desEstadoLote = desEstadoLote;
	}
	
	

}
