package com.institucional.module;

import java.util.Collection;

import javax.ejb.Remote;

import com.institucional.dto.TipoPersonaDTO;

@Remote
public interface TipoPersonaServiceRemote {

	TipoPersonaDTO findById(Integer tipoPersonaId);

	Collection<TipoPersonaDTO> listAll();

}
