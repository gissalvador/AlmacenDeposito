package com.articulo.dto;

import java.io.Serializable;
import java.util.Collection;

public class ArticuloDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer codArticulo;	
	private String codClase;
	private String codItem;
	private String descArticulo;
	private String nomArticulo;
	private Boolean nroSerie;
	private Boolean vto;
	private Integer peso;
	private Integer nroParte;
	private Integer medidaUId;
	private String uMedida;
	private Boolean isComp = false;
	private Boolean act;
	private Integer pesoUId;
	private String uPeso;
	private String grupo;
	private Integer grupoId;
	private Integer materialId;
	private String material;
	private Integer partidaParcialId;
	private Integer tipoArticuloId;
	private String tArticulo;
	private Collection<Integer> compArticulo;
	private String usuario = "admin";
	
	public Integer getCodArticulo() {
		return codArticulo;
	}
	public void setCodArticulo(Integer codArticulo) {
		this.codArticulo = codArticulo;
	}
	
	
	
	public String getCodClase() {
		return codClase;
	}
	public void setCodClase(String codClase) {
		this.codClase = codClase;
	}
	public String getCodItem() {
		return codItem;
	}
	public void setCodItem(String codItem) {
		this.codItem = codItem;
	}
	public Integer getMedidaUId() {
		return medidaUId;
	}
	public void setMedidaUId(Integer medidaUId) {
		this.medidaUId = medidaUId;
	}
	public String getDescArticulo() {
		return descArticulo;
	}
	public void setDescArticulo(String descArticulo) {
		this.descArticulo = descArticulo;
	}
	public String getNomArticulo() {
		return nomArticulo;
	}
	public void setNomArticulo(String nomArticulo) {
		this.nomArticulo = nomArticulo;
	}
	public Boolean getNroSerie() {
		return nroSerie;
	}
	public void setNroSerie(Boolean nroSerie) {
		this.nroSerie = nroSerie;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	public Integer getNroParte() {
		return nroParte;
	}
	public void setNroParte(Integer nroParte) {
		this.nroParte = nroParte;
	}
	public Integer getMedidaU() {
		return medidaUId;
	}
	public void setMedidaU(Integer unidadId) {
		this.medidaUId = unidadId;
	}
	public Boolean getIsComp() {
		return isComp;
	}
	public void setIsComp(Boolean isCond) {
		this.isComp = isCond;
	}
	public Integer getPesoUId() {
		return pesoUId;
	}
	public void setPesoUId(Integer pesoUId) {
		this.pesoUId = pesoUId;
	}
	public Integer getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}
	public Integer getPartidaParcialId() {
		return partidaParcialId;
	}
	public void setPartidaParcialId(Integer partidaParcialId) {
		this.partidaParcialId = partidaParcialId;
	}
	public Integer getTipoArticuloId() {
		return tipoArticuloId;
	}
	public void setTipoArticuloId(Integer tipoArticuloId) {
		this.tipoArticuloId = tipoArticuloId;
	}
	public Collection<Integer> getCompArticulo() {
		return compArticulo;
	}
	public void setCompArticulo(Collection<Integer> compArticulo) {
		this.compArticulo = compArticulo;
	}
	
	
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	public Integer getGrupoId() {
		return grupoId;
	}
	public void setGrupoId(Integer grupoId) {
		this.grupoId = grupoId;
	}
	public Boolean getVto() {
		return vto;
	}
	public void setVto(Boolean vto) {
		this.vto = vto;
	}
	public String getuMedida() {
		return uMedida;
	}
	public void setuMedida(String uMedida) {
		this.uMedida = uMedida;
	}
	public String getuPeso() {
		return uPeso;
	}
	public void setuPeso(String uPeso) {
		this.uPeso = uPeso;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String gettArticulo() {
		return tArticulo;
	}
	public void settArticulo(String tArticulo) {
		this.tArticulo = tArticulo;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Boolean getAct() {
		return act;
	}
	public void setAct(Boolean act) {
		this.act = act;
	}
	
	

}
