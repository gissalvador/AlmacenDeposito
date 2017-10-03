package com.articulo.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.articulo.entities.TipoArticulo;

public class TipoArticuloFactory {
	
	public static TipoArticuloDTO getTipoArticuloDTO(TipoArticulo tipoArticulo) {
		if(tipoArticulo == null) {
			return null;
		}

		TipoArticuloDTO result = new TipoArticuloDTO();
		result.setCodTipoArticulo(tipoArticulo.getCodTipoArticulo());
		result.setNomArticulo(tipoArticulo.getNomArticulo());
		result.setDescArticulo(tipoArticulo.getDescArticulo());
				
		return result;
	}
	
	public static Collection<TipoArticuloDTO> getTipoArticuloDTO(Collection<TipoArticulo> tipoArticulos) {
		if(tipoArticulos == null) {
			return null;
		}

		
		List<TipoArticuloDTO> retorno = new ArrayList<TipoArticuloDTO>();
		for(TipoArticulo tipoArticulo: tipoArticulos) {
			retorno.add(getTipoArticuloDTO(tipoArticulo));
		}
		return retorno;
	}

}
