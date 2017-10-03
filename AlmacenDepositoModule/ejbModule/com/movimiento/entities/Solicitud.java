package com.movimiento.entities;

import java.io.Serializable;
import java.lang.Integer;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Solicitud
 *
 */
@Entity

public class Solicitud implements Serializable {

	   
	@Id
	private String codSolicitud;
	
	private Integer nroPedidoCompra;
	
	private static final long serialVersionUID = 1L;

	public Solicitud() {
		super();
	}   
	public String getCodSolicitud() {
		return this.codSolicitud;
	}

	public void setCodSolicitud(String codSolicitud) {
		this.codSolicitud = codSolicitud;
	}   
	public Integer getNroPedidoCompra() {
		return this.nroPedidoCompra;
	}

	public void setNroPedidoCompra(Integer nroPedidoCompra) {
		this.nroPedidoCompra = nroPedidoCompra;
	}
   
}
