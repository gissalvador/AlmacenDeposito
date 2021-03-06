package com.institucional.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PPrincipalDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int codPPrincipal;
	private Integer nroPPrincipal;
	private String nomPPrincipal;
	private Float movEntrada;
	private Float movSalida;

	private List<PParcialDTO> listPParcialDTO;

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
		
	public Float getMovEntrada() {
		return movEntrada;
	}

	public void setMovEntrada(Float movEntrada) {
		this.movEntrada = movEntrada;
	}

	public Float getMovSalida() {
		return movSalida;
	}

	public void setMovSalida(Float movSalida) {
		this.movSalida = movSalida;
	}

	public List<PParcialDTO> getListPParcialDTO() {
		return listPParcialDTO;
	}

	public void setListPParcialDTO(List<PParcialDTO> listPParcialDTO) {
		this.listPParcialDTO = listPParcialDTO;
	}

	public void addDetalle(PParcialDTO pparcialDTO) {

		if (listPParcialDTO == null) {
			listPParcialDTO = new ArrayList<PParcialDTO>();
		}

		listPParcialDTO.add(pparcialDTO);

	}

}
