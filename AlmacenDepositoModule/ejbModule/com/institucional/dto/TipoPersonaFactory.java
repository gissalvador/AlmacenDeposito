package com.institucional.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.institucional.entities.TipoPersona;


public class TipoPersonaFactory {
	
	public static TipoPersonaDTO getTipoPersonaDTO(TipoPersona tipoPersona) {
		if(tipoPersona == null) {
			return null;
		}

		TipoPersonaDTO result = new TipoPersonaDTO();
		result.setCodTipoPersona(tipoPersona.getCodTipoPersona());
		result.setNomTipoPersona(tipoPersona.getNomTipoPersona());
		
		return result;
	}
	
	public static Collection<TipoPersonaDTO> getTipoPersonaDTO(Collection<TipoPersona> tipoPersonas) {
		if(tipoPersonas == null) {
			return null;
		}

		
		List<TipoPersonaDTO> retorno = new ArrayList<TipoPersonaDTO>();
		for(TipoPersona tipoPersona: tipoPersonas) {
			retorno.add(getTipoPersonaDTO(tipoPersona));
		}
		return retorno;
	}


}
