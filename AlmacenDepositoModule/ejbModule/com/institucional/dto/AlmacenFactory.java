package com.institucional.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.institucional.entities.Almacen;

public class AlmacenFactory {
	
	public static AlmacenDTO getAlmacenDTO(Almacen almacen) {
		if(almacen == null) {
			return null;
		}

		AlmacenDTO result = new AlmacenDTO();
		result.setCodAlmacen(almacen.getCodAlmacen());
		result.setNomAlmacen(almacen.getNomAlmacen());
		result.setNomCentro(almacen.getCentro().getNomCentro());
		
		return result;
	}
	
	public static Collection<AlmacenDTO> getAlmacenDTO(Collection<Almacen> almacenes) {
		if(almacenes == null) {
			return null;
		}

		
		List<AlmacenDTO> retorno = new ArrayList<AlmacenDTO>();
		for(Almacen almacen: almacenes) {
			retorno.add(getAlmacenDTO(almacen));
		}
		return retorno;
	}

}
