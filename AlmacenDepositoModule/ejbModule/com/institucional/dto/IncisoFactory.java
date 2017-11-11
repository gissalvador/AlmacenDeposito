package com.institucional.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.institucional.entities.Inciso;
import com.institucional.entities.PParcial;
import com.institucional.entities.PPrincipal;


public class IncisoFactory {
	
	public static IncisoDTO getIncisoDTO(Inciso inciso) {

		if(inciso == null) {
				return null;
			}

			IncisoDTO result = new IncisoDTO();
			result.setInciso(inciso.getInciso());
			result.setNomInciso(inciso.getNomInciso());
			result.setNroInciso(inciso.getNroInciso());
			result.setMovEntrada(0f);
			result.setMovSalida(0f);
			
			
			for(PPrincipal pprincipal: inciso.getPprincipalId()){
				
				
				PPrincipalDTO pprincipalDTO = new PPrincipalDTO();
				pprincipalDTO.setCodPPrincipal(pprincipal.getCodPPrincipal());
				pprincipalDTO.setNomPPrincipal(pprincipal.getNomPPrincipal());
				pprincipalDTO.setNroPPrincipal(pprincipal.getNroPPrincipal());
				pprincipalDTO.setMovEntrada(0f);
				pprincipalDTO.setMovSalida(0f);
				
				for(PParcial pparcial: pprincipal.getPparcialId()){
					
					PParcialDTO pparcialDTO = new PParcialDTO();
					
					pparcialDTO.setCodPParcial(pparcial.getCodPParcial());
					pparcialDTO.setNroPParcial(pparcial.getNroPParcial());
					pparcialDTO.setNomClasificacion(pparcial.getNomClasificacion());	
					pparcialDTO.setMovEntrada(0f);
					pparcialDTO.setMovSalida(0f);
					
					pprincipalDTO.addDetalle(pparcialDTO);
					
					
				}
			
				result.addDetalle(pprincipalDTO);
				
			}
			
			return result;
		}
		
		public static Collection<IncisoDTO> getIncisoDTO(Collection<Inciso> incisoes) {
			if(incisoes == null) {
				return null;
			}

			
			List<IncisoDTO> retorno = new ArrayList<IncisoDTO>();
			for(Inciso inciso: incisoes) {
				retorno.add(getIncisoDTO(inciso));
			}
			return retorno;
		}

}
