package com.articulo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: HistoriaEstadoArticulo
 *
 */
@Entity

public class HistorialEstadoArticulo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "codHistorialEA")
	private Integer codHistorialEA;
	
	private Date fechaCambioEstado;
	@Column(columnDefinition = "varchar(40)")
	private String usuario;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	private Articulo articulo;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	private EstadoArticulo estadoArticulo;
	


	public HistorialEstadoArticulo() {
		super();
	}   

	public Integer getCodHistorialEA() {
		return codHistorialEA;
	}

	public void setCodHistorialEA(Integer codHistorialEA) {
		this.codHistorialEA = codHistorialEA;
	}

	public Date getFechaCambioEstado() {
		return this.fechaCambioEstado;
	}

	public void setFechaCambioEstado(Date fechaCambioEstado) {
		this.fechaCambioEstado = fechaCambioEstado;
		System.out.println(fechaCambioEstado);
		
	}
	public Articulo getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	public EstadoArticulo getEstadoArticulo() {
		return estadoArticulo;
	}
	public void setEstadoArticulo(EstadoArticulo estadoArticulo) {
		this.estadoArticulo = estadoArticulo;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
   
}
