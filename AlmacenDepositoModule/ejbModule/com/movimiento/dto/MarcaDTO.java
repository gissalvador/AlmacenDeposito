package com.movimiento.dto;

import java.io.Serializable;

public class MarcaDTO  implements Serializable{
	
	/**
	 * 
	 **/
	
	private static final long serialVersionUID = 1L;
	
	private Integer codMarca;
	
	private String nomMarca;

	public Integer getCodMarca() {
		return codMarca;
	}

	public void setCodMarca(Integer codMarca) {
		this.codMarca = codMarca;
	}

	public String getNomMarca() {
		return nomMarca;
	}

	public void setNomMarca(String nomMarca) {
		this.nomMarca = nomMarca;
	}
	
	

}
