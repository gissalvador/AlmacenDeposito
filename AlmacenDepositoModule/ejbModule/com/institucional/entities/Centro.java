package com.institucional.entities;

import java.io.Serializable;
import java.lang.Integer;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: centro
 * 
 */
@Entity
public class Centro implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codCentro;
	@Column(columnDefinition = "varchar(50)")
	private String nomCentro;

	private static final long serialVersionUID = 1L;

	public Centro() {
		super();
	}

	public Integer getCodCentro() {
		return this.codCentro;
	}

	public void setCodCentro(Integer codCentro) {
		this.codCentro = codCentro;
	}

	public String getNomCentro() {
		return nomCentro;
	}

	public void setNomCentro(String nomCentro) {
		this.nomCentro = nomCentro;
	}

}
