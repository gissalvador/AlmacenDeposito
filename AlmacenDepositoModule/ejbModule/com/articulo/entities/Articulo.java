package com.articulo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.persistence.*;

import com.conversion.entities.UnidadMedida;
import com.institucional.entities.PParcial;

/**
 * Son los articulos que participan en los movimeintos del deposito
 * 
 */

@Entity
public class Articulo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codArticulo;

	@Column(columnDefinition = "varchar(10)")
	private String codClase;
	@Column(columnDefinition = "varchar(10)")
	private String codItem;
	@Column(columnDefinition = "varchar(512)")
	private String descArticulo;
	@Column(columnDefinition = "varchar(100)")
	private String nomArticulo;
	private Boolean nroSerie;
	private Boolean vto;
	private Boolean act;
	private Integer peso;
	private Integer nroParte;// depende si es compuesto

	private Boolean isComp;

	@ManyToOne
	@JoinColumn(name = "medidaUId")
	private UnidadMedida medidaU;

	@ManyToOne
	@JoinColumn(name = "pesoUId")
	private UnidadMedida pesoU;

	@ManyToOne
	@JoinColumn(name = "materialId")
	private Material material;

	@ManyToOne
	@JoinColumn(name = "partidaParcialId")
	private PParcial partidaparcial;

	@ManyToOne
	@JoinColumn(name = "tipoArticuloId")
	private TipoArticulo tipoArticulo;

	@ManyToOne
	@JoinColumn(name = "grupoId")
	private Grupo grupo;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "articuloId")
	private List<HistorialEstadoArticulo> historialEstadoArticulo;

	public Articulo() {
		super();
	}

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

	public void setHistorialEstadoArticulo(
			List<HistorialEstadoArticulo> historialEstadoArticulo) {
		this.historialEstadoArticulo = historialEstadoArticulo;
	}

	public String getDescArticulo() {
		return descArticulo;
	}

	public void setDescArticulo(String desArticulo) {
		this.descArticulo = desArticulo;
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

	public Boolean getIsComp() {
		return isComp;
	}

	public void setIsComp(Boolean isComp) {
		this.isComp = isComp;
	}

	public Integer getNroParte() {
		return nroParte;
	}

	public void setNroParte(Integer nroParte) {
		this.nroParte = nroParte;
	}

	public UnidadMedida getMedidaU() {
		return medidaU;
	}

	public void setMedidaU(UnidadMedida medidaU) {
		this.medidaU = medidaU;
	}

	public UnidadMedida getPesoU() {
		return pesoU;
	}

	public void setPesoU(UnidadMedida pesoU) {
		this.pesoU = pesoU;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public PParcial getPartidaparcial() {
		return partidaparcial;
	}

	public void setPartidaparcial(PParcial partidaparcial) {
		this.partidaparcial = partidaparcial;
	}

	public TipoArticulo getTipoArticulo() {
		return tipoArticulo;
	}

	public void setTipoArticulo(TipoArticulo tipoArticulo) {
		this.tipoArticulo = tipoArticulo;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Boolean getVto() {
		return vto;
	}

	public void setVto(Boolean vto) {
		this.vto = vto;
	}

	public Boolean getAct() {
		return act;
	}

	public void setAct(Boolean act) {
		this.act = act;
	}

	public List<HistorialEstadoArticulo> getHistorialEstadoArticulo() {
		return historialEstadoArticulo;
	}

	public void addHistorialEstado(EstadoArticulo estadoA, String usuario) {

		if (historialEstadoArticulo == null) {
			historialEstadoArticulo = new ArrayList<HistorialEstadoArticulo>();
		}

		HistorialEstadoArticulo newOne = new HistorialEstadoArticulo();
		newOne.setArticulo(this);
		newOne.setEstadoArticulo(estadoA);
		newOne.setUsuario(usuario);
		Date date = new Date();
		System.out.println(date);
		newOne.setFechaCambioEstado(date);

		historialEstadoArticulo.add(newOne);

	}

}
