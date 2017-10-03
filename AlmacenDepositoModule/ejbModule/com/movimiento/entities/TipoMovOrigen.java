package com.movimiento.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.persistence.*;

import com.institucional.entities.TipoPersona;

/**
 * Entity implementation class for Entity: TipoMovOrigen
 *
 */
@Entity

public class TipoMovOrigen implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codTMovOrigen;
	private String nomTMovOrigen;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "tipoPersonaId")
	private TipoPersona tipoPersona;
	
	private static final long serialVersionUID = 1L;

	public TipoMovOrigen() {
		super();
	}   
	public Integer getCodTMovOrigen() {
		return this.codTMovOrigen;
	}

	public void setCodTMovOrigen(Integer codTMovOrigen) {
		this.codTMovOrigen = codTMovOrigen;
	}   
	public String getNomTMovOrigen() {
		return this.nomTMovOrigen;
	}

	public void setNomTMovOrigen(String nomTMovOrigen) {
		this.nomTMovOrigen = nomTMovOrigen;
	}
	public TipoPersona getTipoPersona() {
		return tipoPersona;
	}
	public void setTipoPersona(TipoPersona tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	
	
   
}
