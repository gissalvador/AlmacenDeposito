package com.movimiento.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.movimiento.entities.Marca;



public class MarcaFactory {
	
	public static MarcaDTO getMarcaDTO(Marca marca) {
		if(marca == null) {
			return null;
		}

		MarcaDTO result = new MarcaDTO();
		result.setCodMarca(marca.getCodMarca());
		result.setNomMarca(marca.getNomMarca());
					
		return result;
	}
	
	public static Collection<MarcaDTO> getMarcaDTO(Collection<Marca> marcas) {
		if(marcas == null) {
			return null;
		}

		
		List<MarcaDTO> retorno = new ArrayList<MarcaDTO>();
		for(Marca marca: marcas) {
			retorno.add(getMarcaDTO(marca));
		}
		return retorno;
	}

}
