package com.conversion.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity

public class Conversion implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codConversion;
		
	private Float valor;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "unidadOId")
	private TipoUM unidadO;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "unidadDId")
	private TipoUM unidadD;

	public Integer getCodConversion() {
		return codConversion;
	}

	public void setCodConversion(Integer codConversion) {
		this.codConversion = codConversion;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public TipoUM getUnidadO() {
		return unidadO;
	}

	public void setUnidadO(TipoUM unidadO) {
		this.unidadO = unidadO;
	}

	public TipoUM getUnidadD() {
		return unidadD;
	}

	public void setUnidadD(TipoUM unidadD) {
		this.unidadD = unidadD;
	}
	
	

}
