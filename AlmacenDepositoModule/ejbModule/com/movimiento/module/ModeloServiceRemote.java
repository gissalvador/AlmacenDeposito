package com.movimiento.module;

import java.util.Collection;

import javax.ejb.Remote;

import com.movimiento.dto.ModeloDTO;

@Remote
public interface ModeloServiceRemote {

	ModeloDTO findById(Integer modeloId);

	Collection<ModeloDTO> listAll();

	Collection<ModeloDTO> listAllModelos(Integer marcaId);

}
