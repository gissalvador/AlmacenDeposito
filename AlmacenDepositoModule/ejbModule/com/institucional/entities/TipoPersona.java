package com.institucional.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: TipoPersona
 *
 */
@Entity
@Table(name="TipoPersona")

public class TipoPersona implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codTipoPersona;
	@Column(columnDefinition = "varchar(10)")
	private String nomTipoPersona;
	
	private static final long serialVersionUID = 1L;

	public TipoPersona() {
		super();
	}   
	public Integer getCodTipoPersona() {
		return this.codTipoPersona;
	}

	public void setCodTipoPersona(Integer codTipoPersona) {
		this.codTipoPersona = codTipoPersona;
	}   
	public String getNomTipoPersona() {
		return this.nomTipoPersona;
	}

	public void setNomTipoPersona(String nomTipoPersona) {
		this.nomTipoPersona = nomTipoPersona;
	}
   
}
