package com.institucional.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.persistence.*;

/**
 Representa las distintos Unidades Organicas y sus dependencia de la institución.
 * 
 * @author Gisella Salvador
 *
 */
@Entity

public class UnidadOrganica implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codUnidadOrganica;
	
	@Column(columnDefinition = "varchar(100)")
	private String nomUnidaOrganica;
	
	@Column(columnDefinition = "varchar(10)")
	private String nroDependencia;
	
	@OneToOne
	@JoinColumn(name = "uorganicaId")
	private UnidadOrganica depende;
	
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "empleadoId")
	private Empleado esJefe;
	
	private static final long serialVersionUID = 1L;

	public UnidadOrganica() {
		super();
	}   
	public Integer getCodUnidadOrganica() {
		return this.codUnidadOrganica;
	}

	public void setCodUnidadOrganica(Integer codUnidadOrganica) {
		this.codUnidadOrganica = codUnidadOrganica;
	}   
	public String getNomUnidaOrganica() {
		return this.nomUnidaOrganica;
	}

	public void setNomUnidaOrganica(String nomUnidaOrganica) {
		this.nomUnidaOrganica = nomUnidaOrganica;
	}   
   
	public UnidadOrganica getDepende() {
		return this.depende;
	}

	public void setDepende(UnidadOrganica depende) {
		this.depende = depende;
	}
	public Empleado getEsJefe() {
		return esJefe;
	}
	public void setEsJefe(Empleado esJefe) {
		this.esJefe = esJefe;
	}
	public String getNroDependencia() {
		return nroDependencia;
	}
	public void setNroDependencia(String nroDependencia) {
		this.nroDependencia = nroDependencia;
	}
   
	
}
