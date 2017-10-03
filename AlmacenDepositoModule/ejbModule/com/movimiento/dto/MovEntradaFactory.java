package com.movimiento.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.movimiento.entities.DetalleMovEntrada;
import com.movimiento.entities.MovEntrada;




public class MovEntradaFactory {
	
	
	
	public static MovEntradaDTO getMovEntradaDTO(MovEntrada movEntrada) {
		if(movEntrada == null) {
			return null;
		}

		float total = 0;
		
		MovEntradaDTO result = new MovEntradaDTO();
		result.setId(movEntrada.getCodMovEntrada());
		result.setActividad(movEntrada.getActividad().getCodActividad());
		result.setActString(movEntrada.getActividad().getNomActividad());
		result.setComprobante(movEntrada.getMovOrigen().getTipoMovOrigen().getCodTMovOrigen());
		result.setNomComprobante(movEntrada.getMovOrigen().getTipoMovOrigen().getNomTMovOrigen());
		result.setFechaIngreso(movEntrada.getFechaMovEntrada());
		result.setFechaMO(movEntrada.getMovOrigen().getFechaMovOrigen());
		result.setNroComprobante(movEntrada.getMovOrigen().getNroComprobanteMO());
		result.setNroSolicitud(movEntrada.getSolicitud().getNroPedidoCompra());
		result.setImporteTotal(movEntrada.getTotalMovEntrada());
		result.setObservaciones(movEntrada.getObservaciones());
		result.setCuitlegajo(movEntrada.getMovOrigen().getIdPersonaMovOrigen());
		result.setAlmacen(movEntrada.getAlmacen().getCodAlmacen());
		result.setAlmString(movEntrada.getAlmacen().getNomAlmacen());
		result.setCentro(movEntrada.getAlmacen().getCentro().getCodCentro());
		result.setCenString(movEntrada.getAlmacen().getCentro().getNomCentro());
		result.setCodTipMovOrigen(movEntrada.getMovOrigen().getTipoMovOrigen().getTipoPersona().getCodTipoPersona());
		result.setNomTipoMovOrigen(movEntrada.getMovOrigen().getTipoMovOrigen().getTipoPersona().getNomTipoPersona());
		result.setUsuario(movEntrada.getUsuario());
		
		for(DetalleMovEntrada detalleME: movEntrada.getDetalleME()){
		
			
			DetMovEntradaDTO detalleDTO = new DetMovEntradaDTO();
			detalleDTO.setCantidad(detalleME.getCantidadME());
			detalleDTO.setCodArticulo(detalleME.getLote().getArticulo().getCodArticulo());
			detalleDTO.setNomArticulo(detalleME.getLote().getArticulo().getNomArticulo());
			detalleDTO.setFechaVto(detalleME.getLote().getFechaVto());
			detalleDTO.setImporteUnitario(detalleME.getImporteUnitarioME());
			detalleDTO.setMarString(detalleME.getLote().getModelo().getMarca().getNomMarca());
			detalleDTO.setMarca(detalleME.getLote().getModelo().getMarca().getCodMarca());
			detalleDTO.setModString(detalleME.getLote().getModelo().getNomModelo());
			detalleDTO.setModelo(detalleME.getLote().getModelo().getCodModelo());
			detalleDTO.setNroSerie_Proveedor(detalleME.getNroSerie_Proveedor());
			detalleDTO.setLote(detalleME.getLote().getCodLote());
			detalleDTO.setCantidad(detalleME.getCantidadME());
			detalleDTO.setUniTotal(detalleME.getImporteUnitarioME()*detalleME.getCantidadME());
			
			total+= detalleDTO.getUniTotal();
			
			result.addDetalle(detalleDTO);
			
			
		}
			
		result.setTotal(total);
		
		return result;
	}
	
	public static Collection<MovEntradaDTO> getMovEntradaDTO(Collection<MovEntrada> MovEntradaes) {
		if(MovEntradaes == null) {
			return null;
		}

		
		List<MovEntradaDTO> retorno = new ArrayList<MovEntradaDTO>();
		for(MovEntrada MovEntrada: MovEntradaes) {
			retorno.add(getMovEntradaDTO(MovEntrada));
		}
		return retorno;
	}

}
