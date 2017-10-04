package com.movimiento.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Representa los datos el detalle de cada articulo ingresado
 * 
 * @author Gisella Salvador
 * 
 */

public class LoteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codLote;
	private Float stockActual;
	private Float stockInicial;
	private Integer articuloID;
	private String articulo;
	private Date fechaVto;
	private Date fechaCreado;
	private String modString;
	private Integer modelo;
	private Integer marca;
	private String marString;
	private Float cantidad;
	private boolean ajustar;
	private Float importeUnitario;
	private Integer nroEstadoLote;
	private String nomEstadoLote;
	private Integer actividadID;
	private String actividad;

	private List<LoteAlmacenDTO> loteAlmacenDTO;

	public LoteDTO() {

	}

	public String getCodLote() {
		return codLote;
	}

	public void setCodLote(String codLote) {
		this.codLote = codLote;
	}

	public Float getStockActual() {
		return stockActual;
	}

	public void setStockActual(Float stockActual) {
		this.stockActual = stockActual;
	}

	public Float getStockInicial() {
		return stockInicial;
	}

	public void setStockInicial(Float stockInicial) {
		this.stockInicial = stockInicial;
	}

	public Integer getArticuloID() {
		return articuloID;
	}

	public void setArticuloID(Integer articuloID) {
		this.articuloID = articuloID;
	}

	public String getArticulo() {
		return articulo;
	}

	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}

	public Date getFechaVto() {
		return fechaVto;
	}

	public void setFechaVto(Date fechaVto) {
		this.fechaVto = fechaVto;
	}

	public Date getFechaCreado() {
		return fechaCreado;
	}

	public void setFechaCreado(Date fechaCreado) {
		this.fechaCreado = fechaCreado;
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

	public Integer getModelo() {
		return modelo;
	}

	public void setModelo(Integer modelo) {
		this.modelo = modelo;
	}

	public String getMarString() {
		return marString;
	}

	public void setMarString(String marString) {
		this.marString = marString;
	}

	public List<LoteAlmacenDTO> getLoteAlmacenDTO() {
		return loteAlmacenDTO;
	}

	public void setLoteAlmacenDTO(List<LoteAlmacenDTO> loteAlmacenDTO) {
		this.loteAlmacenDTO = loteAlmacenDTO;
	}
	
	

	public Float getCantidad() {
		return cantidad;
	}

	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}

	public boolean isAjustar() {
		return ajustar;
	}

	public void setAjustar(boolean ajustar) {
		this.ajustar = ajustar;
	}
	
	public Float getImporteUnitario() {
		return importeUnitario;
	}

	public void setImporteUnitario(Float importeUnitario) {
		this.importeUnitario = importeUnitario;
	}
		
	public Integer getNroEstadoLote() {
		return nroEstadoLote;
	}

	public void setNroEstadoLote(Integer nroEstadoLote) {
		this.nroEstadoLote = nroEstadoLote;
	}

	public String getNomEstadoLote() {
		return nomEstadoLote;
	}

	public void setNomEstadoLote(String nomEstadoLote) {
		this.nomEstadoLote = nomEstadoLote;
	}
	
	public Integer getActividadID() {
		return actividadID;
	}

	public void setActividadID(Integer actividadID) {
		this.actividadID = actividadID;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public void addLoteAlmacen(LoteAlmacenDTO laDTO) {

		if (loteAlmacenDTO == null) {
			loteAlmacenDTO = new ArrayList<LoteAlmacenDTO>();
		}

		loteAlmacenDTO.add(laDTO);

	}

}
