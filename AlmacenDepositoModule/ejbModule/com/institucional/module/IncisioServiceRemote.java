package com.institucional.module;

import java.util.Collection;

import javax.ejb.Remote;

import com.institucional.dto.IncisoDTO;

@Remote
public interface IncisioServiceRemote {

	IncisoDTO findById(Integer incisoId);

	Collection<IncisoDTO> listAll();

	Collection<IncisoDTO> listRepPresupuesto();

}
