package com.conversion.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.conversion.entities.UnidadMedida;



public class UnidadMedidaFactory {
	
	public static UnidadMedidaDTO getUnidadMedidaDTO(UnidadMedida unidadMedida) {
		if(unidadMedida == null) {
			return null;
		}

		UnidadMedidaDTO result = new UnidadMedidaDTO();
		result.setUnidadMedida(unidadMedida.getUndadMedida());
		result.setNombre(unidadMedida.getNombre());
		result.setSimbolo(unidadMedida.getSimbolo());
		result.setCodTUM(unidadMedida.getTipoUM().getCodTUM());
		result.setNomTipoUM(unidadMedida.getTipoUM().getNomTipoUM());
		
		return result;
	}
	
	public static Collection<UnidadMedidaDTO> getUnidadMedidaDTO(Collection<UnidadMedida> UnidadMedidas) {
		if(UnidadMedidas == null) {
			return null;
		}

		
		List<UnidadMedidaDTO> retorno = new ArrayList<UnidadMedidaDTO>();
		for(UnidadMedida UnidadMedida: UnidadMedidas) {
			retorno.add(getUnidadMedidaDTO(UnidadMedida));
		}
		return retorno;
	}

}
