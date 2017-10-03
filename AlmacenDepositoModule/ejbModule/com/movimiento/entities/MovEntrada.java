package com.movimiento.entities;

import java.io.Serializable;
import java.lang.Float;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.institucional.entities.Actividad;
import com.institucional.entities.Almacen;

/**
 * Entity implementation class for Entity: MovEntrada
 * 
 */
@Entity
public class MovEntrada implements Serializable {

	@Id
	private String codMovEntrada;

	private Date fechaMovEntrada;
	private String observaciones;
	private Float totalMovEntrada;
	private String usuario;
	private Date fechaCreado;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "actividadId")
	private Actividad actividad;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "movorigendId")
	private MovOrigen movOrigen;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "solicitudId")
	private Solicitud solicitud;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "almacenId")
	private Almacen almacen;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DetalleMovEntrada> detalleME;

	private static final long serialVersionUID = 1L;

	public MovEntrada() {
		super();
	}

	public String getCodMovEntrada() {
		return this.codMovEntrada;
	}

	public void setCodMovEntrada(String codMovEntrada) {
		this.codMovEntrada = codMovEntrada;
	}

	public Date getFechaMovEntrada() {
		return this.fechaMovEntrada;
	}

	public void setFechaMovEntrada(Date fechaMovEntrada) {
		this.fechaMovEntrada = fechaMovEntrada;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Float getTotalMovEntrada() {
		return this.totalMovEntrada;
	}

	public void setTotalMovEntrada(Float totalMovEntrada) {
		this.totalMovEntrada = totalMovEntrada;
	}

	public Solicitud getSolicitud() {
		return this.solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	public Actividad getActividad() {
		return this.actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public MovOrigen getMovOrigen() {
		return movOrigen;
	}

	public void setMovOrigen(MovOrigen movOrigen) {
		this.movOrigen = movOrigen;
	}

	public List<DetalleMovEntrada> getDetalleME() {
		return detalleME;
	}

	public void setDetalleME(List<DetalleMovEntrada> detalleME) {
		this.detalleME = detalleME;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFechaCreado() {
		return fechaCreado;
	}

	public void setFechaCreado(Date fechaCreado) {
		this.fechaCreado = fechaCreado;
	}

	public Almacen getAlmacen() {
		return almacen;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

}
