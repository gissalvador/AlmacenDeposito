package com.movimiento.entities;

import java.io.Serializable;

import javax.persistence.*;



/**
 * Entity implementation class for Entity: DetalleMovSalida
 *
 */
@Entity

public class DetalleMovSalida implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String codDetalleMS;
	
	private Float cantidadMS;
	private Float importeUnitarioMS;
	private String nroSerie_Proveedor;

	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "loteId")
	private Lote lote;
	
	public DetalleMovSalida() {
		super();
	}

	public String getCodDetalleMS() {
		return codDetalleMS;
	}

	public void setCodDetalleMS(String codDetalleMS) {
		this.codDetalleMS = codDetalleMS;
	}

	public Float getCantidadMS() {
		return cantidadMS;
	}

	public void setCantidadMS(Float cantidadMS) {
		this.cantidadMS = cantidadMS;
	}

	public Float getImporteUnitarioMS() {
		return importeUnitarioMS;
	}

	public void setImporteUnitarioMS(Float importeUnitarioMS) {
		this.importeUnitarioMS = importeUnitarioMS;
	}

	public String getNroSerie_Proveedor() {
		return nroSerie_Proveedor;
	}

	public void setNroSerie_Proveedor(String nroSerie_Proveedor) {
		this.nroSerie_Proveedor = nroSerie_Proveedor;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}
	
	
   
}
