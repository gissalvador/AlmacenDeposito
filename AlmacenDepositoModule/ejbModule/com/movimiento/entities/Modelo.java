package com.movimiento.entities;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.*;

import com.movimiento.entities.Marca;

/**
 * Entity implementation class for Entity: ModeloArticulo
 * 
 */
@Entity
public class Modelo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codModelo;

	private String nomModelo;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "marcaId")
	private Marca marca;

	private static final long serialVersionUID = 1L;

	public Modelo() {
		super();
	}

	public String getNomModelo() {
		return this.nomModelo;
	}

	public void setNomModelo(String nomModelo) {
		this.nomModelo = nomModelo;
	}

	public Integer getCodModelo() {
		return codModelo;
	}

	public void setCodModelo(Integer codModelo) {
		this.codModelo = codModelo;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

}
