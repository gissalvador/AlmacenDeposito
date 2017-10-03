package com.institucional.entities;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class PParcial implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codPParcial;
	private int nroPParcial;
	private String nomClasificacion;
	
	
	public int getCodPParcial() {
		return codPParcial;
	}
	public void setCodPParcial(int codPParcial) {
		this.codPParcial = codPParcial;
	}
	public int getNroPParcial() {
		return nroPParcial;
	}
	public void setNroPParcial(int nroPParcial) {
		this.nroPParcial = nroPParcial;
	}
	public String getNomClasificacion() {
		return nomClasificacion;
	}
	public void setNomClasificacion(String nomClasificacion) {
		this.nomClasificacion = nomClasificacion;
	}


	

}
