package com.articulo.entities;

import java.io.Serializable;
import java.lang.String;




import javax.persistence.*;

/**
 * Entity implementation class for Entity: EstadoArticulo
 *
 */
@Entity

public class EstadoArticulo implements Serializable {

	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codEstadoArticulo;
	
	@Column(columnDefinition = "varchar(40)")
	private String nomEstadoArticulo;
	
	public EstadoArticulo() {
		super();
	}   
	public int getCodEstadoArticulo() {
		return this.codEstadoArticulo;
	}

	public void setCodEstadoArticulo(int codEstadoArticulo) {
		this.codEstadoArticulo = codEstadoArticulo;
	}   
	public String getNomEstadoArticulo() {
		return this.nomEstadoArticulo;
	}

	public void setNomEstadoArticulo(String nomEstadoArticulo) {
		this.nomEstadoArticulo = nomEstadoArticulo;
	}


   
}
