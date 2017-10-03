package com.institucional.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Actividad
 * 
 */
@Entity
public class Actividad implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codActividad;

	private Integer nroActividad;

	private String redProgramatica;
	@Column(columnDefinition = "varchar(100)")
	private String nomActividad;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "empleadoId")
	private Empleado responsable;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "subresponsabledId")
	private Empleado subresponsable;

	private static final long serialVersionUID = 1L;

	public Actividad() {
		super();
	}

	public Integer getCodActividad() {
		return this.codActividad;
	}

	public void setCodActividad(Integer codActividad) {
		this.codActividad = codActividad;
	}

	public Integer getNroActividad() {
		return this.nroActividad;
	}

	public void setNroActividad(Integer nroActividad) {
		this.nroActividad = nroActividad;
	}

	public String getRedProgramatica() {
		return this.redProgramatica;
	}

	public void setRedProgramatica(String redProgramatica) {
		this.redProgramatica = redProgramatica;
	}

	public String getNomActividad() {
		return this.nomActividad;
	}

	public void setNomActividad(String nomActividad) {
		this.nomActividad = nomActividad;
	}

	public Empleado getResponsable() {
		return responsable;
	}

	public void setResponsable(Empleado responsable) {
		this.responsable = responsable;
	}

	public Empleado getSubresponsable() {
		return subresponsable;
	}

	public void setSubresponsable(Empleado subresponsable) {
		this.subresponsable = subresponsable;
	}

}
