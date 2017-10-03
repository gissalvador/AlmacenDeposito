package com.institucional.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PPrincipalDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int codPPrincipal;
	private Integer nroPPrincipal;
	private String nomPPrincipal;

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
