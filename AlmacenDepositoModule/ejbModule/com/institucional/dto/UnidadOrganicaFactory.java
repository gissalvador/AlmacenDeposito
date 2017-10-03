package com.institucional.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.institucional.entities.UnidadOrganica;

public class UnidadOrganicaFactory {
	
	public static UnidadOrganicaDTO getUnidadOrganicaDTO(UnidadOrganica unidadOrganica) {
		if(unidadOrganica == null) {
			return null;
		}

		UnidadOrganicaDTO result = new UnidadOrganicaDTO();
		result.setCodUnidadOrganica(unidadOrganica.getCodUnidadOrganica());
		result.setNomUnidaOrganica(unidadOrganica.getNomUnidaOrganica());
		result.setNroDependencia(unidadOrganica.getNroDependencia());
		if(unidadOrganica.getEsJefe() != null){
			result.setCodPersona(unidadOrganica.getEsJefe().getCodPersona());
			result.setNomPersona(unidadOrganica.getEsJefe().getNomPersona());
			result.setLegajo(unidadOrganica.getEsJefe().getLegajo());
		}
		
		if(unidadOrganica.getDepende() != null){
			
			result.setDepende(unidadOrganica.getDepende().getCodUnidadOrganica());
			result.setNroDepende(unidadOrganica.getDepende().getNroDependencia());
			result.setNomDepende(unidadOrganica.getDepende().getNomUnidaOrganica());
			
		}
		
		
						
		return result;
	}
	
	public static Collection<UnidadOrganicaDTO> getUnidadOrganicaDTO(Collection<UnidadOrganica> unidadOrganicas) {
		if(unidadOrganicas == null) {
			return null;
		}

		
		List<UnidadOrganicaDTO> retorno = new ArrayList<UnidadOrganicaDTO>();
		for(UnidadOrganica unidadOrganica: unidadOrganicas) {
			retorno.add(getUnidadOrganicaDTO(unidadOrganica));
		}
		return retorno;
	}
	

}
