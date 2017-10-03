package com.institucional.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.institucional.entities.PParcial;

public class PParcialFactory {
	
	public static PParcialDTO getPParcialDTO(PParcial pparcial) {

		if(pparcial == null) {
				return null;
			}

			PParcialDTO result = new PParcialDTO();
			result.setNroPParcial(pparcial.getNroPParcial());
			result.setNomClasificacion(pparcial.getNomClasificacion());
			
			return result;
		}
		
		public static Collection<PParcialDTO> getPParcialDTO(Collection<PParcial> pparciales) {
			if(pparciales == null) {
				return null;
			}

			
			List<PParcialDTO> retorno = new ArrayList<PParcialDTO>();
			for(PParcial pparcial: pparciales) {
				retorno.add(getPParcialDTO(pparcial));
			}
			return retorno;
		}

	

}
