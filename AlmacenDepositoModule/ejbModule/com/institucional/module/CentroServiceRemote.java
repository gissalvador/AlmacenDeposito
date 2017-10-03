package com.institucional.module;

import java.util.Collection;

import javax.ejb.Remote;

import com.institucional.dto.CentroDTO;

@Remote
public interface CentroServiceRemote {

	CentroDTO findById(Integer centroId);

	Collection<CentroDTO> listAll();

	void addCentro(CentroDTO centro);

}
