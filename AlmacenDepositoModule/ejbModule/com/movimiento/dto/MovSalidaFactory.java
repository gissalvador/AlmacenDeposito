package com.movimiento.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.movimiento.entities.DetalleMovSalida;
import com.movimiento.entities.MovSalida;

public class MovSalidaFactory {
	

	
	
	public static MovSalidaDTO getMovSalidaDTO(MovSalida movSalida) {
		if(movSalida == null) {
			return null;
		}

		float total = 0;
		
		MovSalidaDTO result = new MovSalidaDTO();
		result.setId(movSalida.getCodMovSalida());
		result.setActividad(movSalida.getActividad().getNroActividad());
		result.setNomActividad(movSalida.getActividad().getNomActividad());
		result.setComprobante(movSalida.getMovOrigen().getTipoMovOrigen().getCodTMovOrigen());
		result.setNomComprobante(movSalida.getMovOrigen().getTipoMovOrigen().getNomTMovOrigen());
		result.setFechaSalida(movSalida.getFechaMovSalida());
		//result.setFechaMO(movSalida.getMovOrigen().getFechaMovOrigen());
		result.setNroComprobante(movSalida.getMovOrigen().getNroComprobanteMO());
		//result.setNroSolicitud(movSalida.getSolicitud().getNroPedidoCompra());
		result.setImporteTotal(movSalida.getTotalMovSalida());
		result.setObservaciones(movSalida.getObservaciones());
		result.setLegajo(movSalida.getMovOrigen().getIdPersonaMovOrigen());
		result.setAlmacen(movSalida.getAlmacen().getCodAlmacen());
		result.setNomAlmacen(movSalida.getAlmacen().getNomAlmacen());
		result.setCentro(movSalida.getAlmacen().getCentro().getCodCentro());
		result.setCenString(movSalida.getAlmacen().getCentro().getNomCentro());
		result.setCodTipMovOrigen(movSalida.getMovOrigen().getTipoMovOrigen().getTipoPersona().getCodTipoPersona());
		result.setNomTipoMovOrigen(movSalida.getMovOrigen().getTipoMovOrigen().getTipoPersona().getNomTipoPersona());
		result.setUsuario(movSalida.getUsuario());
		
		for(DetalleMovSalida detalleMS: movSalida.getDetalleMS()){
		
			
			DetMovSalidaDTO detalleDTO = new DetMovSalidaDTO();
			detalleDTO.setId(detalleMS.getCodDetalleMS());
			detalleDTO.setCantidad(detalleMS.getCantidadMS());
			detalleDTO.setCodArticulo(detalleMS.getLote().getArticulo().getCodArticulo());
			detalleDTO.setNomArticulo(detalleMS.getLote().getArticulo().getNomArticulo());
			detalleDTO.setFechaVto(detalleMS.getLote().getFechaVto());
			detalleDTO.setImporteUnitario(detalleMS.getImporteUnitarioMS());
			detalleDTO.setMarString(detalleMS.getLote().getModelo().getMarca().getNomMarca());
			detalleDTO.setMarca(detalleMS.getLote().getModelo().getMarca().getCodMarca());
			detalleDTO.setModString(detalleMS.getLote().getModelo().getNomModelo());
			detalleDTO.setModelo(detalleMS.getLote().getModelo().getCodModelo());
			detalleDTO.setNroSerie_Proveedor(detalleMS.getNroSerie_Proveedor());
			detalleDTO.setLote(detalleMS.getLote().getCodLote());
			detalleDTO.setCantidad(detalleMS.getCantidadMS());
			detalleDTO.setUniTotal(detalleMS.getImporteUnitarioMS()*detalleMS.getCantidadMS());
			detalleDTO.setPparcial(detalleMS.getLote().getArticulo().getPartidaparcial().getNroPParcial());
			
			total+= detalleDTO.getUniTotal();
			
			result.addDetalleMS(detalleDTO);
			
			
		}
			
		result.setImporteTotal(total);
		
		return result;
	}
	
	public static Collection<MovSalidaDTO> getMovSalidaDTO(Collection<MovSalida> MovSalidaes) {
		if(MovSalidaes == null) {
			return null;
		}

		
		List<MovSalidaDTO> retorno = new ArrayList<MovSalidaDTO>();
		for(MovSalida MovSalida: MovSalidaes) {
			retorno.add(getMovSalidaDTO(MovSalida));
		}
		return retorno;
	}

}
