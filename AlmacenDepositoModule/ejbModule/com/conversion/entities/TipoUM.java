package com.conversion.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity

public class TipoUM implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codTUM;
	@Column(columnDefinition = "varchar(50)")
	private String nomTipoUM;

	public Integer getCodTUM() {
		return codTUM;
	}

	public void setCodTUM(Integer codTUM) {
		this.codTUM = codTUM;
	}

	public String getNomTipoUM() {
		return nomTipoUM;
	}

	public void setNomTipoUM(String nomTipoUM) {
		this.nomTipoUM = nomTipoUM;
	}
	
	
	

}
