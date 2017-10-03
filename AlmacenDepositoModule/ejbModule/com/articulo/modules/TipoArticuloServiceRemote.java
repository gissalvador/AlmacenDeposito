package com.articulo.modules;

import java.util.Collection;

import javax.ejb.Remote;

import com.articulo.dto.TipoArticuloDTO;

@Remote
public interface TipoArticuloServiceRemote {

	TipoArticuloDTO findById(Integer tipoArticuloId);

	Collection<TipoArticuloDTO> listAll();

}
