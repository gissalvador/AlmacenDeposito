package com.institucional.module;

import java.util.Collection;

import javax.ejb.Remote;

import com.institucional.dto.UnidadOrganicaDTO;

@Remote
public interface UnidadOrganicaServiceRemote {

	UnidadOrganicaDTO findById(Integer unidadOrganicaId);

	Collection<UnidadOrganicaDTO> listAll();

	void addUnidadOrganica(UnidadOrganicaDTO unidadOrganica);

}
