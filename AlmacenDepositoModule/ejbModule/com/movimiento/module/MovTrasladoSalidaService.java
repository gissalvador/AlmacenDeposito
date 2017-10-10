package com.movimiento.module;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.institucional.entities.Actividad;
import com.institucional.module.ActividadService;
import com.institucional.repository.AlmacenRepository;
import com.movimiento.dto.DetMovSalidaDTO;
import com.movimiento.dto.LoteDTO;
import com.movimiento.dto.LoteFactory;
import com.movimiento.dto.MovSalidaDTO;
import com.movimiento.entities.DetalleMovEntrada;
import com.movimiento.entities.DetalleMovSalida;
import com.movimiento.entities.Lote;
import com.movimiento.entities.LoteAlmacen;
import com.movimiento.entities.MovOrigen;
import com.movimiento.entities.MovSalida;
import com.movimiento.repository.DetalleMERepository;
import com.movimiento.repository.DetalleMSrepository;
import com.movimiento.repository.LoteRepository;
import com.movimiento.repository.MovSalidaRepository;

/**
 * Session Bean implementation class MovTrasladoSalidaService
 */

@Stateless
@LocalBean
public class MovTrasladoSalidaService implements MovTrasladoSalidaServiceRemote {
	
	@EJB
	private LoteRepository lRepository;
	
	@EJB
	private LoteService lservice;
	
	@EJB
	private DetalleMERepository detentradaRepository;
	
	@EJB
	private DetalleMSrepository detRepository;
	
	@EJB
	private AlmacenRepository almRepository;
	
	@EJB
	private ActividadService aService;
	
	@EJB
	private MovOrigenService mos;
	
	@EJB
	private MovSalidaRepository msRepository;
	
	@EJB
	private MovTasladoSalidaValidations validator;
	
	@EJB
	private	DetalleTrasladoSalidaValidation validator2;
	
	


    /**
     * Default constructor. 
     */
    public MovTrasladoSalidaService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Collection<LoteDTO> buscarLotes(MovSalidaDTO movTrasladoSalida,
			Collection<DetMovSalidaDTO> detallesMovTrasladoSalida) {
		// TODO Auto-generated method stub
		
		List<ValidationError> errors = validator.validaraddMovSalida(movTrasladoSalida);

		if (errors.size() > 0) {
			throw new BusinessException("Errores al agregar el Movimiento de Salida.", errors);
		}
		
		for (DetMovSalidaDTO ddto : detallesMovTrasladoSalida) {

			errors = validator2.validarDetalle(ddto);
			if (errors.size() > 0) {
				throw new BusinessException("Errores al agregar el Movimiento de Entrada.", errors);
			}

		}
		
		List<LoteDTO> listlDTO = new ArrayList<LoteDTO>();
		
		List<Lote> lotes = new ArrayList<Lote>();

		for (DetMovSalidaDTO ddto : detallesMovTrasladoSalida) {
			
			float cantidad = ddto.getCantidad();
			
			Lote l = lRepository.get(ddto.getLote());
			
			lotes.add(l);		
			
			for (LoteAlmacen la : l.getLoteAlmacenes()) {
				
				if (la.getAlmacen().getCodAlmacen() == movTrasladoSalida.getAlmacen()) {

					LoteDTO lDTO = new LoteDTO();

					if (la.getCantidad() >= cantidad && cantidad != 0) {
						
						
						lDTO = LoteFactory.getLoteDTO(l);

						lDTO.setCantidad(cantidad);

						cantidad = 0;

						listlDTO.add(lDTO);
						
						
					}  else if (la.getCantidad() <= cantidad && cantidad != 0) {

						lDTO = LoteFactory.getLoteDTO(l);

						lDTO.setCantidad(la.getCantidad());

						listlDTO.add(lDTO);

						cantidad -= la.getCantidad();

					}

										
				}
				
			}
			
			
		}
		
		return listlDTO;
	}

	@Override
	public void crearMovTrasladoSalida(MovSalidaDTO movTrasladoSalida) {
		// TODO Auto-generated method stub
		
		Float importetotal = (float) 0;

		List<DetalleMovSalida> detList;

		detList = new ArrayList<DetalleMovSalida>();

		int contAjuste = 0;

		for (DetMovSalidaDTO ddto : movTrasladoSalida.getDetalleMS()) {
			
			Lote lote = lRepository.get(ddto.getLoteId());

			if (ddto.isAjustar()) {

				lservice.addHistorialEstadoLote(3, lote, movTrasladoSalida.getUsuario());

				contAjuste += 1;

			} else {
				
				for (LoteAlmacen la : lote.getLoteAlmacenes()) {

					if (la.getAlmacen().getCodAlmacen().equals(movTrasladoSalida.getAlmacen())) {

						Float cantidad = la.getCantidad() - ddto.getCantidad();

						la.setCantidad(cantidad);

					}
					
					Float stock = lote.getStockActual() - ddto.getCantidad();

					lote.setStockActual(stock);

					DetalleMovSalida dms = addDetalleMovSalida(ddto.getCantidad(), lote);

					importetotal += dms.getImporteUnitarioMS() * dms.getCantidadMS();

					detList.add(dms);

					break;
						
				
			}
							
					lservice.addHistorialEstadoLote(7, lote, movTrasladoSalida.getUsuario());

						
			}
			
		}
		
		if (movTrasladoSalida.getDetalleMS().size() != contAjuste) {

			movTrasladoSalida.setImporteTotal(importetotal);

			addMovSalida(movTrasladoSalida, detList);
		}

		
	}

	
	public DetalleMovSalida addDetalleMovSalida(float cant, Lote l) {

		String np = null;
		Float iu = null;

		DetalleMovSalida dms = new DetalleMovSalida();

		List<String> ls = detentradaRepository.buscarxLote(l.getCodLote());

		for (String s : ls) {

			DetalleMovEntrada dme = new DetalleMovEntrada();

			dme = detentradaRepository.get(s);

			np = dme.getNroSerie_Proveedor();
			iu = dme.getImporteUnitarioME();

			break;

		}

		dms.setCantidadMS(cant);
		dms.setImporteUnitarioMS(iu);
		dms.setNroSerie_Proveedor(np);
		dms.setLote(l);

		detRepository.add(dms);

		return dms;
	}
	
	public MovSalida addMovSalida(MovSalidaDTO movSalida, List<DetalleMovSalida> detList) {

		MovOrigen mo = mos.addMovOrigen(movSalida, 7);

		Actividad act = aService.getActividad(movSalida.getActividad());

		MovSalida movSalidaNuevo = new MovSalida();

		movSalidaNuevo.setFechaMovSalida(movSalida.getFechaSalida());
		movSalidaNuevo.setObservaciones(movSalida.getObservaciones());
		movSalidaNuevo.setTotalMovSalida(movSalida.getImporteTotal());
		movSalidaNuevo.setActividad(act);
		movSalidaNuevo.setMovOrigen(mo);
		movSalidaNuevo.setDetalleME(detList);
		movSalidaNuevo.setAlmacen(almRepository.get(movSalida.getAlmacen()));
		movSalidaNuevo.setUsuario(movSalida.getUsuario());
		Date date = new Date();
		movSalidaNuevo.setFechaCreado(date);

		msRepository.add(movSalidaNuevo);

		return movSalidaNuevo;

	}
	
}
