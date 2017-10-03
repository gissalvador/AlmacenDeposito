package com.movimiento.entities;

import java.io.Serializable;
import java.lang.String;




import javax.persistence.*;

/**
 * Entity implementation class for Entity: EstadoArticulo
 *
 */
@Entity

public class EstadoLote implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codEstadoLote;
	
	@Column(columnDefinition = "varchar(40)")
	private String nomEstadoLote;
	
	private String desEstadoLote;
	
	public EstadoLote() {
		super();
	}

	public int getCodEstadoLote() {
		return codEstadoLote;
	}

	public void setCodEstadoLote(int codEstadoLote) {
		this.codEstadoLote = codEstadoLote;
	}

	public String getNomEstadoLote() {
		return nomEstadoLote;
	}

	public void setNomEstadoLote(String nomEstadoLote) {
		this.nomEstadoLote = nomEstadoLote;
	}

	public String getDesEstadoLote() {
		return desEstadoLote;
	}

	public void setDesEstadoLote(String desEstadoLote) {
		this.desEstadoLote = desEstadoLote;
	}   

	


   
}
