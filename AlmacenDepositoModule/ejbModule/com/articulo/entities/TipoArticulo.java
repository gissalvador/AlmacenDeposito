package com.articulo.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.persistence.*;

/**
 * Representa los distintos usos para los articulos como puede ser materia prima.
 *
 */
@Entity
public class TipoArticulo implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codTipoArticulo;
	private String descArticulo;
	private String nomArticulo;
	private static final long serialVersionUID = 1L;

	public TipoArticulo() {
		super();
	}   
	public Integer getCodTipoArticulo() {
		return this.codTipoArticulo;
	}

	public void setCodTipoArticulo(Integer codTipoArticulo) {
		this.codTipoArticulo = codTipoArticulo;
	}   
	public String getDescArticulo() {
		return this.descArticulo;
	}

	public void setDescArticulo(String descArticulo) {
		this.descArticulo = descArticulo;
	}   
	public String getNomArticulo() {
		return this.nomArticulo;
	}

	public void setNomArticulo(String nomArticulo) {
		this.nomArticulo = nomArticulo;
	}
   
}
