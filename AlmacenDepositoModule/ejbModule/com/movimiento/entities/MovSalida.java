package com.movimiento.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.institucional.entities.Actividad;
import com.institucional.entities.Almacen;
import com.institucional.entities.Empleado;

/**
 * Entity implementation class for Entity: MovSalida
 *
 */
@Entity

public class MovSalida implements Serializable {

	@Id
	private String codMovSalida;
	private Date fechaMovSalida;
	private String observaciones;
	private Float totalMovSalida;
	private String usuario;
	private Date fechaCreado;
		
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "actividadId")
	private Actividad actividad;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "almacenId")
	private Almacen almacen;

			
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "solicitudId")
	private Solicitud solicitud;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "movorigendId")
	private MovOrigen movOrigen;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DetalleMovSalida> detalleMS;
	
	private static final long serialVersionUID = 1L;

	public MovSalida() {
		super();
	}

	public String getCodMovSalida() {
		return codMovSalida;
	}

	public void setCodMovSalida(String codMovSalida) {
		this.codMovSalida = codMovSalida;
	}

	public Date getFechaMovSalida() {
		return fechaMovSalida;
	}

	public void setFechaMovSalida(Date fechaMovSalida) {
		this.fechaMovSalida = fechaMovSalida;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Float getTotalMovSalida() {
		return totalMovSalida;
	}

	public void setTotalMovSalida(Float totalMovSalida) {
		this.totalMovSalida = totalMovSalida;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	public List<DetalleMovSalida> getDetalleMS() {
		return detalleMS;
	}

	public void setDetalleME(List<DetalleMovSalida> detalleMS) {
		this.detalleMS = detalleMS;
	}

	public MovOrigen getMovOrigen() {
		return movOrigen;
	}

	public void setMovOrigen(MovOrigen movOrigen) {
		this.movOrigen = movOrigen;
	}

	public void setDetalleMS(List<DetalleMovSalida> detalleMS) {
		this.detalleMS = detalleMS;
	}
	
	public Date getFechaCreado() {
		return fechaCreado;
	}

	public void setFechaCreado(Date fechaCreado) {
		this.fechaCreado = fechaCreado;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Almacen getAlmacen() {
		return almacen;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

	
   
	
}
