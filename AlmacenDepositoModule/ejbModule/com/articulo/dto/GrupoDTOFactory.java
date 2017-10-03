package com.articulo.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.articulo.entities.Grupo;


public class GrupoDTOFactory {
	
	public static GrupoDTO getGrupoDTO(Grupo grupo) {
		if(grupo == null) {
			return null;
		}

		GrupoDTO result = new GrupoDTO();
		result.setCodGrupo(grupo.getCodGrupo());
		result.setNomGrupo(grupo.getNomGrupo());
		result.setDesGrupo(grupo.getDescGrupo());
				
		return result;
	}
	
	public static Collection<GrupoDTO> getGrupoDTO(Collection<Grupo> grupoes) {
		if(grupoes == null) {
			return null;
		}

		
		List<GrupoDTO> retorno = new ArrayList<GrupoDTO>();
		for(Grupo grupo: grupoes) {
			retorno.add(getGrupoDTO(grupo));
		}
		return retorno;
	}

}
