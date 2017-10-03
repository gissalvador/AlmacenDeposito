package com.institucional.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.institucional.entities.Centro;


public class CentroFactory {

	public static CentroDTO getCentroDTO(Centro centro) {

		if(centro == null) {
				return null;
			}

			CentroDTO result = new CentroDTO();
			result.setCodCentro(centro.getCodCentro());
			result.setNomCentro(centro.getNomCentro());
			
			
			return result;
		}
		
		public static Collection<CentroDTO> getCentroDTO(Collection<Centro> centros) {
			if(centros == null) {
				return null;
			}

			
			List<CentroDTO> retorno = new ArrayList<CentroDTO>();
			for(Centro centro: centros) {
				retorno.add(getCentroDTO(centro));
			}
			return retorno;
		}

	
}
