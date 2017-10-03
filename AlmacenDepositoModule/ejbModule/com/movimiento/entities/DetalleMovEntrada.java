package com.movimiento.entities;

import java.io.Serializable;
import java.lang.Float;
import java.lang.String;

import javax.persistence.*;



/**
 * Entity implementation class for Entity: DetalleMovEntrada
 *
 */
@Entity

public class DetalleMovEntrada implements Serializable {

	@Id
	private String codDetalleME;
	
	private Float cantidadME;
	private Float importeUnitarioME;
	private String nroSerie_Proveedor;
	private String usuario;
	
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "loteId")
	private Lote lote;
	
	
	
	private static final long serialVersionUID = 1L;

	public DetalleMovEntrada() {
		super();
	}   
	public String getCodDetalleME() {
		return this.codDetalleME;
	}

	public void setCodDetalleME(String codDetalleME) {
		this.codDetalleME = codDetalleME;
	}   
	public Float getCantidadME() {
		return this.cantidadME;
	}

	public void setCantidadME(Float cantidadME) {
		this.cantidadME = cantidadME;
	}   
	public Float getImporteUnitarioME() {
		return this.importeUnitarioME;
	}

	public void setImporteUnitarioME(Float importeUnitarioME) {
		this.importeUnitarioME = importeUnitarioME;
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
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
	
   
}
