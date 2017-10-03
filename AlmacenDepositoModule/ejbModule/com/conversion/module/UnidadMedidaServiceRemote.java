package com.conversion.module;

import java.util.Collection;

import javax.ejb.Remote;

import com.conversion.dto.UnidadMedidaDTO;

@Remote
public interface UnidadMedidaServiceRemote {

	UnidadMedidaDTO findById(Integer unidadMedidaId);

	Collection<UnidadMedidaDTO> listAll();

}
