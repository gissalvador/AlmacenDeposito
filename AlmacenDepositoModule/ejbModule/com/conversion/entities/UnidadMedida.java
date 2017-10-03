package com.conversion.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity

public class UnidadMedida implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer undadMedida;
	@Column(columnDefinition = "varchar(50)")
	private String nombre;
	@Column(columnDefinition = "varchar(10)")
	private String simbolo;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "tipoUMId")
	private TipoUM tipoUM;

	public Integer getUndadMedida() {
		return undadMedida;
	}

	public void setUndadMedida(Integer undadMedida) {
		this.undadMedida = undadMedida;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public TipoUM getTipoUM() {
		return tipoUM;
	}

	public void setTipoUM(TipoUM tipoUM) {
		this.tipoUM = tipoUM;
	}
	
	
	

}
