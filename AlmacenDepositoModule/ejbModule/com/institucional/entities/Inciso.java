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
public class Inciso implements Serializable {
	
private static final long serialVersionUID = 1L;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int inciso;
private int nroInciso;
private String nomInciso;

@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
private List<PPrincipal> pprincipalId;

public int getInciso() {
	return inciso;
}
public void setInciso(int inciso) {
	this.inciso = inciso;
}
public int getNroInciso() {
	return nroInciso;
}
public void setNroInciso(int nroInciso) {
	this.nroInciso = nroInciso;
}
public String getNomInciso() {
	return nomInciso;
}
public void setNomInciso(String nomInciso) {
	this.nomInciso = nomInciso;
}
public List<PPrincipal> getPprincipalId() {
	return pprincipalId;
}
public void setPprincipalId(List<PPrincipal> pprincipalId) {
	this.pprincipalId = pprincipalId;
}




}
