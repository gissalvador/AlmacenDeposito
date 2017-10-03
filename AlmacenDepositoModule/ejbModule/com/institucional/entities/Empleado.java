package com.institucional.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Empleado
 *
 */
@Entity

public class Empleado implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codPersona;
	
	private String nomPersona;
	@Column(columnDefinition = "varchar(20)")
	private String cuil;
	@Column(columnDefinition = "varchar(25)")
	private String telefono;
	@Column(columnDefinition = "varchar(25)")
	private String celular;
	
	private String domicilio;
	@Column(columnDefinition = "varchar(10)")
	private String legajo;
	@Column(columnDefinition = "varchar(10)")
	private String conResPatriminial;
	
	private String mail;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "unidadOrganicaId")
	private UnidadOrganica unidadOrganica;
	
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "tipoPersonaId")
	private TipoPersona tipoPersona;
	
	private static final long serialVersionUID = 1L;

	public Empleado() {
		super();
	}

	public Integer getCodPersona() {
		return codPersona;
	}

	public void setCodPersona(Integer codPersona) {
		this.codPersona = codPersona;
	}

	public String getNomPersona() {
		return nomPersona;
	}

	public void setNomPersona(String nomPersona) {
		this.nomPersona = nomPersona;
	}

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	public String getConResPatriminial() {
		return conResPatriminial;
	}

	public void setConResPatriminial(String conResPatriminial) {
		this.conResPatriminial = conResPatriminial;
	}

	public UnidadOrganica getUnidadOrganica() {
		return unidadOrganica;
	}

	public void setUnidadOrganica(UnidadOrganica unidadOrganica) {
		this.unidadOrganica = unidadOrganica;
	}

	public TipoPersona getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(TipoPersona tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}  
	
	
	
	
	
}
