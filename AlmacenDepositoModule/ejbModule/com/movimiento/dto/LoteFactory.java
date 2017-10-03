package com.movimiento.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.movimiento.entities.Lote;
import com.movimiento.entities.LoteAlmacen;




public class LoteFactory {
	
	public static LoteDTO getLoteDTO(Lote lote) {
		if(lote == null) {
			return null;
		}

		LoteDTO result = new LoteDTO();
		result.setCodLote(lote.getCodLote());
		result.setArticuloID(lote.getArticulo().getCodArticulo());
		result.setArticulo(lote.getArticulo().getNomArticulo());
		result.setStockInicial(lote.getStockInicial());
		result.setStockActual(lote.getStockActual());
		result.setMarca(lote.getModelo().getMarca().getCodMarca());
		result.setMarString(lote.getModelo().getMarca().getNomMarca());
		result.setModString(lote.getModelo().getNomModelo());
		result.setModelo(lote.getModelo().getCodModelo());
		result.setFechaCreado(lote.getFechaCreado());
		result.setFechaVto(lote.getFechaVto());
		result.setImporteUnitario(lote.getImporteUnitario());
		result.setNroEstadoLote(lote.getEstadoActualLote().getCodEstadoLote());
		result.setNomEstadoLote(lote.getEstadoActualLote().getNomEstadoLote());
		result.setActividad(lote.getActividad().getNomActividad());
		result.setActividadID(lote.getActividad().getNroActividad());
		
		for(LoteAlmacen loteAlmacen: lote.getLoteAlmacenes()){
				
					
					LoteAlmacenDTO ldDTO = new LoteAlmacenDTO();
					ldDTO.setCantidad(loteAlmacen.getCantidad());
					ldDTO.setAlmacen(loteAlmacen.getAlmacen().getNomAlmacen());
					ldDTO.setAlmacenId(loteAlmacen.getAlmacen().getCodAlmacen());
					ldDTO.setUbicacion(loteAlmacen.getUbicacion());
							
					
					result.addLoteAlmacen(ldDTO);					
					
				}
				
		return result;
	}
	
	public static Collection<LoteDTO> getLoteDTO(Collection<Lote> Lotes) {
		if(Lotes == null) {
			return null;
		}

		
		List<LoteDTO> retorno = new ArrayList<LoteDTO>();
		for(Lote Lote: Lotes) {
			retorno.add(getLoteDTO(Lote));
		}
		return retorno;
	}

}
