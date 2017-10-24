package com.seguridad.dto;

import java.io.Serializable;
import java.util.Date;


public class CambioClaveDTO implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private int codCClave;
	
	private String usuario;

	private String codRecuperar;
	
	private Boolean usado;
	
	private Date finVigencia;


	
	public int getCodCClave() {
		return codCClave;
	}

	public void setCodCClave(int codCClave) {
		this.codCClave = codCClave;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCodRecuperar() {
		return codRecuperar;
	}

	public void setCodRecuperar(String codRecuperar) {
		this.codRecuperar = codRecuperar;
	}

	public Boolean getUsado() {
		return usado;
	}

	public void setUsado(Boolean usado) {
		this.usado = usado;
	}

	public Date getFinVigencia() {
		return finVigencia;
	}

	public void setFinVigencia(Date finVigencia) {
		this.finVigencia = finVigencia;
	}
	
	

}
