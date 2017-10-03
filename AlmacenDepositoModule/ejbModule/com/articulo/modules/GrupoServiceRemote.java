package com.articulo.modules;

import java.util.Collection;

import javax.ejb.Remote;

import com.articulo.dto.GrupoDTO;

@Remote
public interface GrupoServiceRemote {

	GrupoDTO findById(Integer grupoId);

	Collection<GrupoDTO> listAll();

}
