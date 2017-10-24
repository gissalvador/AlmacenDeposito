package com.seguridad.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Size;


/**
 * Entity implementation class for Entity: CambioClave
 *
 */
@Entity

public class CambioClave implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codCClave;
	
	
	@Size(max = 128)
	private String usuario;
	
	@Size(max = 128)
	private String codRecuperar;
	
	private Boolean usado;
	
	@Temporal(TemporalType.DATE)
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
