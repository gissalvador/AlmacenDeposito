package com.institucional.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Almacen
 * 
 */
@Entity
public class Almacen implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codAlmacen;
	@Column(columnDefinition = "varchar(50)")
	private String nomAlmacen;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "centroId")
	private Centro centro;

	private static final long serialVersionUID = 1L;

	public Almacen() {
		super();
	}

	public Integer getCodAlmacen() {
		return this.codAlmacen;
	}

	public void setCodAlmacen(Integer codAlmacen) {
		this.codAlmacen = codAlmacen;
	}

	public String getNomAlmacen() {
		return this.nomAlmacen;
	}

	public void setNomAlmacen(String nomAlmacen) {
		this.nomAlmacen = nomAlmacen;
	}

	public Centro getCentro() {
		return this.centro;
	}

	public void setCentro(Centro Centro) {
		this.centro = Centro;
	}

}
