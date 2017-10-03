package com.articulo.modules;

import java.util.Collection;

import javax.ejb.Remote;

import com.articulo.dto.ArticuloDTO;


@Remote
public interface ArticuloServicesNewRemote {

	void addArticulo(ArticuloDTO articulo);

	ArticuloDTO findById(Integer articuloId);

	Collection<ArticuloDTO> listAll();

}
