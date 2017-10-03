package com.institucional.entities;

import com.institucional.entities.TipoPersona;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Proveedor
 *
 */
@Entity

public class Proveedor implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codPersona;
	
	private String razonSocial;
	@Column(columnDefinition = "varchar(20)")
	private String cuit;
	@Column(columnDefinition = "varchar(25)")
	private String telefono;
	@Column(columnDefinition = "varchar(25)")
	private String celular;
	
	private String domicilio;
	@Column(columnDefinition = "varchar(100)")
	private String alias;
	
	private String mail;
	@Column(columnDefinition = "varchar(50)")
	private String nroProveedor;
	@Column(columnDefinition = "varchar(10)")
	private String responsable;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "tipoPersonaId")
	private TipoPersona tipoPersona;
	
	
	private static final long serialVersionUID = 1L;

	public Proveedor() {
		super();
	}

	public Integer getCodPersona() {
		return codPersona;
	}

	public void setCodPersona(Integer codPersona) {
		this.codPersona = codPersona;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNroProveedor() {
		return nroProveedor;
	}

	public void setNroProveedor(String nroProveedor) {
		this.nroProveedor = nroProveedor;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public TipoPersona getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(TipoPersona tipoPersona) {
		this.tipoPersona = tipoPersona;
	} 
	
	

   
	
}
