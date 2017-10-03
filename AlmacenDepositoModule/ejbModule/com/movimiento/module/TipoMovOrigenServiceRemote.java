package com.movimiento.module;

import java.util.Collection;

import javax.ejb.Remote;

import com.movimiento.dto.TipoMovOrigenDTO;

@Remote
public interface TipoMovOrigenServiceRemote {

	TipoMovOrigenDTO findById(Integer tipoMovOrigenId);

	Collection<TipoMovOrigenDTO> listAll();

	void addTipoMovOrigen(TipoMovOrigenDTO tipoMovOrigen);

}
