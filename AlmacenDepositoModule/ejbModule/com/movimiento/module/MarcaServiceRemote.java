package com.movimiento.module;

import java.util.Collection;

import javax.ejb.Remote;

import com.movimiento.dto.MarcaDTO;

@Remote
public interface MarcaServiceRemote {

	MarcaDTO findById(Integer marcaId);

	Collection<MarcaDTO> listAll();



}
