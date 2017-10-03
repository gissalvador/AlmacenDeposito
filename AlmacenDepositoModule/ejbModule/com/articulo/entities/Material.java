package com.articulo.entities;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: MaterialArticulo
 *
 */
@Entity

public class Material implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codMaterial;
	@Column(columnDefinition = "varchar(100)")
	private String nomMaterial;
	private String desMaterial;
	@Column(columnDefinition = "varchar(100)")
	private String tipoMaterial;
	
	private static final long serialVersionUID = 1L;

	public Material() {
		super();
	}   
	public Integer getCodMaterial() {
		return this.codMaterial;
	}

	public void setCodMaterial(Integer codMaterial) {
		this.codMaterial = codMaterial;
	}   
	public String getNomMaterial() {
		return this.nomMaterial;
	}

	public void setNomMaterial(String nomMaterial) {
		this.nomMaterial = nomMaterial;
	}
	public String getDesMaterial() {
		return desMaterial;
	}
	public void setDesMaterial(String desMaterial) {
		this.desMaterial = desMaterial;
	}
	public String getTipoMaterial() {
		return tipoMaterial;
	}
	public void setTipoMaterial(String tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}
	
	
	
   
}
