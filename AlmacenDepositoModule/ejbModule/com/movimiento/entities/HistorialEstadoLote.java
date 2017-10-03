package com.movimiento.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: HistoriaEstadoArticulo
 *
 */
@Entity

public class HistorialEstadoLote implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "codHistorialEL")
	private Integer codHistorialEL;
	
	private Date fechaCambioEstado;
	@Column(columnDefinition = "varchar(40)")
	private String usuario;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	private Lote lote;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	private EstadoLote estadoLote;
	


	public HistorialEstadoLote() {
		super();
	}



	public Integer getCodHistorialEL() {
		return codHistorialEL;
	}



	public void setCodHistorialEL(Integer codHistorialEL) {
		this.codHistorialEL = codHistorialEL;
	}



	public Date getFechaCambioEstado() {
		return fechaCambioEstado;
	}



	public void setFechaCambioEstado(Date fechaCambioEstado) {
		this.fechaCambioEstado = fechaCambioEstado;
	}



	public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	public Lote getLote() {
		return lote;
	}



	public void setLote(Lote lote) {
		this.lote = lote;
	}



	public EstadoLote getEstadoLote() {
		return estadoLote;
	}



	public void setEstadoLote(EstadoLote estadoLote) {
		this.estadoLote = estadoLote;
	}   

	
	
   
}
