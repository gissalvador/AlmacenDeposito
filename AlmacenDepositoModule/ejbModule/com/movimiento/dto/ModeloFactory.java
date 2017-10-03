package com.movimiento.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.movimiento.entities.Modelo;

public class ModeloFactory {
	
	public static ModeloDTO getModeloDTO(Modelo modelo) {
		if(modelo == null) {
			return null;
		}

		ModeloDTO result = new ModeloDTO();
		result.setCodModelo(modelo.getCodModelo());
		result.setNomModelo(modelo.getNomModelo());
		result.setMarcaId(modelo.getMarca().getCodMarca());
		result.setMarca(modelo.getMarca().getNomMarca());
					
		return result;
	}
	
	public static Collection<ModeloDTO> getModeloDTO(Collection<Modelo> modelos) {
		if(modelos == null) {
			return null;
		}

		
		List<ModeloDTO> retorno = new ArrayList<ModeloDTO>();
		for(Modelo modelo: modelos) {
			retorno.add(getModeloDTO(modelo));
		}
		return retorno;
	}

}
