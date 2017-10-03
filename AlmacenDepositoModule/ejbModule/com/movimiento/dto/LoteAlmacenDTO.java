package com.movimiento.dto;

import java.io.Serializable;

public class LoteAlmacenDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String almacen;
	private Integer almacenId;
	private String ubicacion;
	private Float cantidad;
	public String getAlmacen() {
		return almacen;
	}
	public void setAlmacen(String almacen) {
		this.almacen = almacen;
	}
	public Integer getAlmacenId() {
		return almacenId;
	}
	public void setAlmacenId(Integer almacenId) {
		this.almacenId = almacenId;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Float getCantidad() {
		return cantidad;
	}
	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}
	
	
	

}
