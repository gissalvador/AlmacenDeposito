package com.movimiento.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Representa los datos el detalle de cada articulo ingresado
 * 
 * @author Gisella Salvador
 * 
 */

public class DetMovEntradaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	   private Float cantidad;
	   private Integer codArticulo;
	   private String nomArticulo;
	   private Integer pparcial;
	   private Integer modelo;
	   private String modString;
	   private Integer marca;
	   private String marString;	   
	   private Float importeUnitario;
	   private String ubicacion;
	   private Date fechaVto;
	   private String nroSerie_Proveedor;
	   private String lote;
	   private Float uniTotal;
	   private boolean ajustar;
	
		
	   
	   
	   
	   
	public DetMovEntradaDTO() {
		
		
	}

	
	
	public String getNomArticulo() {
		return nomArticulo;
	}



	public void setNomArticulo(String nomArticulo) {
		this.nomArticulo = nomArticulo;
	}



	public String getModString() {
		return modString;
	}


	public void setModString(String modString) {
		this.modString = modString;
	}

	

	public String getMarString() {
		return marString;
	}


	public void setMarString(String marString) {
		this.marString = marString;
	}


	public Float getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}
	
	public Integer getCodArticulo() {
		return codArticulo;
	}
	
	public void setCodArticulo(Integer codArticulo) {
		this.codArticulo = codArticulo;
	}
	
	public String getUbicacion() {
		return ubicacion;
	}
	
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public Integer getModelo() {
		return modelo;
	}
	
	public void setModelo(Integer modelo) {
		this.modelo = modelo;
	}
	
	public Integer getMarca() {
		return marca;
	}
	
	public void setMarca(Integer marca) {
		this.marca = marca;
	}
	

	public Date getFechaVto() {
		return fechaVto;
	}
	
	public void setFechaVto(Date fechaVto) {
		this.fechaVto = fechaVto;
	}
	

	public Float getImporteUnitario() {
		return importeUnitario;
	}


	public void setImporteUnitario(Float importeUnitario) {
		this.importeUnitario = importeUnitario;
	}


	public String getNroSerie_Proveedor() {
		return nroSerie_Proveedor;
	}


	public void setNroSerie_Proveedor(String nroSerie_Proveedor) {
		this.nroSerie_Proveedor = nroSerie_Proveedor;
	}



	public String getLote() {
		return lote;
	}



	public void setLote(String lote) {
		this.lote = lote;
	}



	public Float getUniTotal() {
		return uniTotal;
	}



	public void setUniTotal(Float uniTotal) {
		this.uniTotal = uniTotal;
	}



	public boolean isAjustar() {
		return ajustar;
	}



	public void setAjustar(boolean ajustar) {
		this.ajustar = ajustar;
	}


	public Integer getPparcial() {
		return pparcial;
	}



	public void setPparcial(Integer pparcial) {
		this.pparcial = pparcial;
	}

	
	   
}
