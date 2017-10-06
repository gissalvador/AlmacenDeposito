package com.movimiento.dto;

import java.io.Serializable;
import java.util.Date;

public class DetMovSalidaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private Float cantidad;
	private Integer codArticulo;
	private String nomArticulo;
	private String loteId;
	private String ubicacion;
	private String nroSerie_Proveedor;
	private float precioU;
	private boolean ajustar;
	private Date fechaVto;
	private Float importeUnitario;
	private Integer modelo;
	private String modString;
	private Integer marca;
	private String marString;
	private String lote;
	private Float uniTotal;
    private Float cantDevolucion = null;
	
	 

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

	public String getNomArticulo() {
		return nomArticulo;
	}

	public void setNomArticulo(String nomArticulo) {
		this.nomArticulo = nomArticulo;
	}

	public String getLoteId() {
		return loteId;
	}

	public void setLoteId(String loteId) {
		this.loteId = loteId;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getNroSerie_Proveedor() {
		return nroSerie_Proveedor;
	}

	public void setNroSerie_Proveedor(String nroSerie_Proveedor) {
		this.nroSerie_Proveedor = nroSerie_Proveedor;
	}

	public float getPrecioU() {
		return precioU;
	}

	public void setPrecioU(float precioU) {
		this.precioU = precioU;
	}

	public boolean isAjustar() {
		return ajustar;
	}

	public void setAjustar(boolean ajustar) {
		this.ajustar = ajustar;
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

	public Integer getModelo() {
		return modelo;
	}

	public void setModelo(Integer modelo) {
		this.modelo = modelo;
	}

	public String getModString() {
		return modString;
	}

	public void setModString(String modString) {
		this.modString = modString;
	}

	public Integer getMarca() {
		return marca;
	}

	public void setMarca(Integer marca) {
		this.marca = marca;
	}

	public String getMarString() {
		return marString;
	}

	public void setMarString(String marString) {
		this.marString = marString;
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

	public Float getCantDevolucion() {
		return cantDevolucion;
	}

	public void setCantDevolucion(Float cantDevolucion) {
		this.cantDevolucion = cantDevolucion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
	


}
