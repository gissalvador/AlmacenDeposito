package com.movimiento.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.movimiento.entities.TipoMovOrigen;


public class TipoMovOrigenFactory {
	
	public static TipoMovOrigenDTO getTipoMovOrigenDTO(TipoMovOrigen tipoMovOrigen) {

		if(tipoMovOrigen == null) {
				return null;
			}

			TipoMovOrigenDTO result = new TipoMovOrigenDTO();
			result.setCodTMovOrigen(tipoMovOrigen.getCodTMovOrigen());
			result.setNomTMovOrigen(tipoMovOrigen.getNomTMovOrigen());
			result.setNroTipoPersona(tipoMovOrigen.getTipoPersona().getCodTipoPersona());
			result.setTipoPersona(tipoMovOrigen.getTipoPersona().getNomTipoPersona());
			
			
			return result;
		}
		
		public static Collection<TipoMovOrigenDTO> getTipoMovOrigenDTO(Collection<TipoMovOrigen> tipoMovOrigenes) {
			if(tipoMovOrigenes == null) {
				return null;
			}

			
			List<TipoMovOrigenDTO> retorno = new ArrayList<TipoMovOrigenDTO>();
			for(TipoMovOrigen tipoMovOrigen: tipoMovOrigenes) {
				retorno.add(getTipoMovOrigenDTO(tipoMovOrigen));
			}
			return retorno;
		}

	

}
