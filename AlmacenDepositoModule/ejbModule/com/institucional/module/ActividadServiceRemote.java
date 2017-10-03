package com.institucional.module;

import java.util.Collection;

import javax.ejb.Remote;

import com.institucional.dto.ActividadDTO;

@Remote
public interface ActividadServiceRemote {

	ActividadDTO findById(Integer actividadId);

	Collection<ActividadDTO> listAll();

}
