package com.movimiento.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: MovOrigen
 *
 */
@Entity

public class MovOrigen implements Serializable {

	   
	@Id
	private String codMovOrigen;
	
	private String nroComprobanteMO;
	private Date fechaMovOrigen;
	private String idPersonaMovOrigen;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "tipoMovOrigenId")
	private TipoMovOrigen tipoMovOrigen;
	
	
	private static final long serialVersionUID = 1L;

	public MovOrigen() {
		super();
	}   
	
	public String getNroComprobanteMO() {
		return nroComprobanteMO;
	}

	public void setNroComprobanteMO(String nroComprobanteMO) {
		this.nroComprobanteMO = nroComprobanteMO;
	}

	public String getCodMovOrigen() {
		return this.codMovOrigen;
	}

	public void setCodMovOrigen(String codMovOrigen) {
		this.codMovOrigen = codMovOrigen;
	}   
	public Date getFechaMovOrigen() {
		return this.fechaMovOrigen;
	}

	public void setFechaMovOrigen(Date fechaMovOrigen) {
		this.fechaMovOrigen = fechaMovOrigen;
	}   
	
	public String getIdPersonaMovOrigen() {
		return idPersonaMovOrigen;
	}

	public void setIdPersonaMovOrigen(String idPersonaMovOrigen) {
		this.idPersonaMovOrigen = idPersonaMovOrigen;
	}

	public TipoMovOrigen getTipoMovOrigen() {
		return this.tipoMovOrigen;
	}

	public void setTipoMovOrigen(TipoMovOrigen tipoMovOrigen) {
		this.tipoMovOrigen = tipoMovOrigen;
	}
   
}
