package com.institucional.module;

import java.util.Collection;

import javax.ejb.Remote;

import com.institucional.dto.EmpleadoDTO;

@Remote
public interface EmpleadoServiceRemote {

	EmpleadoDTO findById(Integer empleadoId);

	Collection<EmpleadoDTO> listAll();

}
