package com.movimiento.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class MovSalidaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	 private String id;
	 private Integer almacen;
	 private String nomAlmacen;
	 private Integer actividad;
	 private String nomActividad;
	 private Date fechaSalida;
     private Integer nroSolicitud;
     private String legajo;
     private String nomPersona;
     private String observaciones;
     private Float importeTotal;
     private Integer comprobante;
     private String nomComprobante;
     private String nroComprobante;
     private String usuario;
     private Integer centro;
     private String CenString;
     private Integer codTipMovOrigen;
     private String nomTipoMovOrigen;
     
     private List<DetMovSalidaDTO> detalleMS;
	
     public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getAlmacen() {
		return almacen;
	}
	public void setAlmacen(Integer almacen) {
		this.almacen = almacen;
	}
	public Integer getActividad() {
		return actividad;
	}
	public void setActividad(Integer actividad) {
		this.actividad = actividad;
	}
	public Date getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public Integer getNroSolicitud() {
		return nroSolicitud;
	}
	public void setNroSolicitud(Integer nroSolicitud) {
		this.nroSolicitud = nroSolicitud;
	}
	
	public String getLegajo() {
		return legajo;
	}
	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Float getImporteTotal() {
		return importeTotal;
	}
	public void setImporteTotal(Float importeTotal) {
		this.importeTotal = importeTotal;
	}
	public String getNomAlmacen() {
		return nomAlmacen;
	}
	public void setNomAlmacen(String nomAlmacen) {
		this.nomAlmacen = nomAlmacen;
	}
	public String getNomActividad() {
		return nomActividad;
	}
	public void setNomActividad(String nomActividad) {
		this.nomActividad = nomActividad;
	}
	public String getNomPersona() {
		return nomPersona;
	}
	public void setNomPersona(String nomPersona) {
		this.nomPersona = nomPersona;
	}
	public List<DetMovSalidaDTO> getDetalleMS() {
		return detalleMS;
	}
	public void setDetalleMS(List<DetMovSalidaDTO> detalleMS) {
		this.detalleMS = detalleMS;
	}
	
	public void addDetalleMS(DetMovSalidaDTO detalleDTO){
		
		if (detalleMS == null) {
			detalleMS = new ArrayList<DetMovSalidaDTO>();
		}
		
			
		detalleMS.add(detalleDTO);
			
	}
	public Integer getComprobante() {
		return comprobante;
	}
	public void setComprobante(Integer comprobante) {
		this.comprobante = comprobante;
	}
	public String getNomComprobante() {
		return nomComprobante;
	}
	public void setNomComprobante(String nomComprobante) {
		this.nomComprobante = nomComprobante;
	}
	public String getNroComprobante() {
		return nroComprobante;
	}
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Integer getCentro() {
		return centro;
	}
	public void setCentro(Integer centro) {
		this.centro = centro;
	}
	public String getCenString() {
		return CenString;
	}
	public void setCenString(String cenString) {
		CenString = cenString;
	}
	public Integer getCodTipMovOrigen() {
		return codTipMovOrigen;
	}
	public void setCodTipMovOrigen(Integer codTipMovOrigen) {
		this.codTipMovOrigen = codTipMovOrigen;
	}
	public String getNomTipoMovOrigen() {
		return nomTipoMovOrigen;
	}
	public void setNomTipoMovOrigen(String nomTipoMovOrigen) {
		this.nomTipoMovOrigen = nomTipoMovOrigen;
	}
	
	
    
     
	

}
