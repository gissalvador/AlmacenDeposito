package com.movimiento.entities;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.*;

/**
 * Marca del articulo
 * 
 */
@Entity
public class Marca implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codMarca;
	@Column(columnDefinition = "varchar(100)")
	private String nomMarca;

	public Marca() {
		super();
	}

	public Integer getCodMarca() {
		return this.codMarca;
	}

	public void setCodMarca(Integer codMarca) {
		this.codMarca = codMarca;
	}

	public String getNomMarca() {
		return this.nomMarca;
	}

	public void setNomMarca(String nomMarca) {
		this.nomMarca = nomMarca;
	}

}
