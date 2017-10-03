package com.movimiento.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Representa los datos necesarios para relizar un movimiento
 * 
 * @author Gisella Salvador
 * 
 */

public class MovEntradaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	 private String id;
	 private Integer almacen;
	 private String almString;
	 private Integer actividad;
	 private String actString;
     private Date fechaIngreso;
     private String nroComprobante;
     private Date fechaMO;
     private Integer nroSolicitud;
     private String persona;
     private String cuitlegajo;
     private String nombre;
     private String observaciones;
     private Integer comprobante;
     private String nomComprobante;
     private Float importeTotal;
     private String usuario;
     private Integer centro;
     private String cenString;
     private Integer codTipMovOrigen;
     private String nomTipoMovOrigen;
     private String perPro;
     private String nomPerPro;
     private Float total;
     
     private List<DetMovEntradaDTO> listDetalleDTO;
     
	
    public String getId() {
 		return id;
 	}

 	public void setId(String id) {
 		this.id = id;
 	} 
     
 	
    public String getAlmString() {
		return almString;
	}

	public void setAlmString(String almString) {
		this.almString = almString;
	}

	public String getActString() {
		return actString;
	}

	public void setActString(String actString) {
		this.actString = actString;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNomComprobante() {
		return nomComprobante;
	}

	public void setNomComprobante(String nomComprobante) {
		this.nomComprobante = nomComprobante;
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
	
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
	public String getNroComprobante() {
		return nroComprobante;
	}
	
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}
	
	
	public String getCuitlegajo() {
		return cuitlegajo;
	}

	public void setCuitlegajo(String cuitlegajo) {
		this.cuitlegajo = cuitlegajo;
	}

	public Date getFechaMO() {
		return fechaMO;
	}

	public void setFechaMO(Date fechaMO) {
		this.fechaMO = fechaMO;
	}

	public Integer getNroSolicitud() {
		return nroSolicitud;
	}
	
	public void setNroSolicitud(Integer nroSolicitud) {
		this.nroSolicitud = nroSolicitud;
	}
	
	public String getPersona() {
		return persona;
	}
	
	public void setPersona(String persona) {
		this.persona = persona;
	}
	
	
	
	public String getObservaciones() {
		return observaciones;
	}
	
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	public Integer getComprobante() {
		return comprobante;
	}
	
	public void setComprobante(Integer comprobante) {
		this.comprobante = comprobante;
	}

	public Float getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(Float importeTotal) {
		this.importeTotal = importeTotal;
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
		return cenString;
	}

	public void setCenString(String cenString) {
		this.cenString = cenString;
	}

	public List<DetMovEntradaDTO> getListDetalleDTO() {
		return listDetalleDTO;
	}

	public void setListDetalleDTO(List<DetMovEntradaDTO> listDetalleDTO) {
		this.listDetalleDTO = listDetalleDTO;
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

	public void addDetalle(DetMovEntradaDTO detalleDTO){
		
		if (listDetalleDTO == null) {
			listDetalleDTO = new ArrayList<DetMovEntradaDTO>();
		}
		
			
		listDetalleDTO.add(detalleDTO);
			
	}

	public String getPerPro() {
		return perPro;
	}

	public void setPerPro(String perPro) {
		this.perPro = perPro;
	}

	public String getNomPerPro() {
		return nomPerPro;
	}

	public void setNomPerPro(String nomPerPro) {
		this.nomPerPro = nomPerPro;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}
	
	
	

}
