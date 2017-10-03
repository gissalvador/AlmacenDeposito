package com.articulo.entities;

import java.io.Serializable;
import java.lang.String;



import javax.persistence.*;

/**
 * Entity implementation class for Entity: Grupo
 *
 */
@Entity

public class Grupo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codGrupo;
	@Column(columnDefinition = "varchar(50)")
	private String nomGrupo;
	private String descGrupo;
	
	private static final long serialVersionUID = 1L;

	public Grupo() {
		super();
	}   
	public int getCodGrupo() {
		return this.codGrupo;
	}

	public void setCodGrupo(int codGrupo) {
		this.codGrupo = codGrupo;
	}   
	public String getNomGrupo() {
		return this.nomGrupo;
	}

	public void setNomGrupo(String nomGrupo) {
		this.nomGrupo = nomGrupo;
	}   
	public String getDescGrupo() {
		return this.descGrupo;
	}

	public void setDescGrupo(String descGrupo) {
		this.descGrupo = descGrupo;
	}
   
}
