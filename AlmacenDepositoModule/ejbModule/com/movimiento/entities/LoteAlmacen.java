package com.movimiento.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.institucional.entities.Almacen;

@Entity

public class LoteAlmacen implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "codLoteAlmacen")
	private Long codLoteAlmacen;
	@Column(columnDefinition = "varchar(20)")
	private String ubicacion;
	
	private Float cantidad;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	private Lote lote;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	private Almacen almacen;

	
	
	public Long getCodLoteAlmacen() {
		return codLoteAlmacen;
	}

	public void setCodLoteAlmacen(Long codLoteAlmacen) {
		this.codLoteAlmacen = codLoteAlmacen;
	}

	public Float getCantidad() {
		return cantidad;
	}

	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public Almacen getAlmacen() {
		return almacen;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

   
}

