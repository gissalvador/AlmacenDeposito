package com.institucional.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PPrincipal implements Serializable {
	
private static final long serialVersionUID = 1L;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int codPPrincipal;
private Integer nroPPrincipal;
private String nomPPrincipal;

@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
private List<PParcial> pparcialId;




public int getCodPPrincipal() {
	return codPPrincipal;
}


public void setCodPPrincipal(int codPPrincipal) {
	this.codPPrincipal = codPPrincipal;
}


public Integer getNroPPrincipal() {
	return nroPPrincipal;
}


public void setNroPPrincipal(Integer nroPPrincipal) {
	this.nroPPrincipal = nroPPrincipal;
}


public String getNomPPrincipal() {
	return nomPPrincipal;
}


public void setNomPPrincipal(String nomPPrincipal) {
	this.nomPPrincipal = nomPPrincipal;
}


public List<PParcial> getPparcialId() {
	return pparcialId;
}


public void setPparcialId(List<PParcial> pparcialId) {
	this.pparcialId = pparcialId;
}




}
