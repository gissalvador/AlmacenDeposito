package com.institucional.module;

import java.util.Collection;

import javax.ejb.Remote;

import com.institucional.dto.ProveedorDTO;

@Remote
public interface ProveedorServiceRemote {

	ProveedorDTO findById(Integer proveedorId);

	Collection<ProveedorDTO> listAll();

}
