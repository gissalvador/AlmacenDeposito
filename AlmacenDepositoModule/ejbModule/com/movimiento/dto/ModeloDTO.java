package com.movimiento.dto;

import java.io.Serializable;

public class ModeloDTO implements Serializable{
	
	/**
	 * 
	 **/
	
	private static final long serialVersionUID = 1L;
	
	private Integer codModelo;
	
	private String nomModelo;
	
	private String marca;
	
	private Integer marcaId;

	
	public Integer getCodModelo() {
		return codModelo;
	}

	public void setCodModelo(Integer codModelo) {
		this.codModelo = codModelo;
	}

	public String getNomModelo() {
		return nomModelo;
	}

	public void setNomModelo(String nomModelo) {
		this.nomModelo = nomModelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getMarcaId() {
		return marcaId;
	}

	public void setMarcaId(Integer marcaId) {
		this.marcaId = marcaId;
	}
	
			

}
