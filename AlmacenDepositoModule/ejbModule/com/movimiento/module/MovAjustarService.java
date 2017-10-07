package com.movimiento.module;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.application.exceptions.BusinessException;
import com.institucional.entities.Actividad;
import com.institucional.module.ActividadService;
import com.institucional.repository.AlmacenRepository;
import com.movimiento.dto.DetMovEntradaDTO;
import com.movimiento.dto.DetMovSalidaDTO;
import com.movimiento.dto.LoteDTO;
import com.movimiento.dto.LoteFactory;
import com.movimiento.dto.MovEntradaDTO;
import com.movimiento.dto.MovSalidaDTO;
import com.movimiento.entities.DetalleMovEntrada;
import com.movimiento.entities.DetalleMovSalida;
import com.movimiento.entities.Lote;
import com.movimiento.entities.LoteAlmacen;
import com.movimiento.entities.MovEntrada;
import com.movimiento.entities.MovOrigen;
import com.movimiento.entities.MovSalida;
import com.movimiento.entities.Solicitud;
import com.movimiento.repository.DetalleMERepository;
import com.movimiento.repository.DetalleMSrepository;
import com.movimiento.repository.LoteRepository;
import com.movimiento.repository.MovEntradaRepository;
import com.movimiento.repository.MovSalidaRepository;

/**
 * Session Bean implementation class MovAjustarService
 */
@Stateless
@LocalBean
public class MovAjustarService implements MovAjustarServiceRemote {

	@EJB
	private LoteRepository lRepository;

	@EJB
	private LoteService ls;

	@EJB
	private DetalleMERepository detRepository;

	@EJB
	private DetalleMERepository detentradaRepository;

	@EJB
	private ActividadService aService;

	@EJB
	private AlmacenRepository almRepository;

	@EJB
	private MovOrigenService mos;

	@EJB
	private SolicitudService ss;

	@EJB
	private MovEntradaRepository meRepository;
	
	@EJB
	private MovSalidaRepository msRepository;
	
	@EJB
	private DetalleMSrepository detSRepository;

	/**
	 * Default constructor.
	 */
	public MovAjustarService() {
		// TODO Auto-generated constructor stub

	}

	@Override
	public LoteDTO buscarLotes(String codLote) {
		// TODO Auto-generated method stub
		
		Lote lote = lRepository.get(codLote);
		
		if (lote.getEstadoActualLote().getCodEstadoLote() != 3) {
			throw new BusinessException("Errores: el Lote debe estar en estado \"Ajustar\". y se encuenta en: " + lote.getEstadoActualLote().getNomEstadoLote());
		}
		
		LoteDTO loteDTO = new LoteDTO();

		loteDTO = LoteFactory.getLoteDTO(lote);

		return loteDTO;

	}

	@Override
	public void crearMovAjustar(MovEntradaDTO movAjustar) {
		// TODO Auto-generated method stub

		Float importetotal = (float) 0;

		List<DetalleMovEntrada> detList;
		detList = new ArrayList<DetalleMovEntrada>();

		for (DetMovEntradaDTO ddto : movAjustar.getListDetalleDTO()) {

			Lote l = lRepository.get(ddto.getLote());

			ls.addLoteAlmacen(movAjustar.getAlmacen(), l, ddto.getCantidad(), null);

			DetalleMovEntrada dme = addDetalleMovEntrada(ddto);

			dme.setLote(l);

			importetotal += dme.getCantidadME() * dme.getImporteUnitarioME();

			detList.add(dme);

			if (l.getStockActual() > 0) {

				ls.addHistorialEstadoLote(2, l, movAjustar.getUsuario());

			}

		}

		movAjustar.setImporteTotal(importetotal);
		MovEntrada movDevolucionNuevo = addMovEntrada(movAjustar, detList);
		System.out.println(movDevolucionNuevo.getCodMovEntrada());

	}

	public DetalleMovEntrada addDetalleMovEntrada(DetMovEntradaDTO ddto) {

		String np = null;
		Float iu = null;

		DetalleMovEntrada dme = new DetalleMovEntrada();

		List<String> ls = detentradaRepository.buscarxLote(ddto.getLote());

		for (String s : ls) {

			DetalleMovEntrada dme2 = new DetalleMovEntrada();

			dme2 = detentradaRepository.get(s);

			np = dme2.getNroSerie_Proveedor();
			iu = dme2.getImporteUnitarioME();

			break;

		}

		dme.setCantidadME(ddto.getCantidad());
		dme.setImporteUnitarioME(iu);
		;
		dme.setNroSerie_Proveedor(np);

		detRepository.add(dme);

		return dme;
	}

	public MovEntrada addMovEntrada(MovEntradaDTO movAjustar, List<DetalleMovEntrada> detList) {

		Actividad act = aService.getActividad(movAjustar.getActividad());

		Solicitud s = ss.addSolicitud(movAjustar);

		MovOrigen mo = mos.addMovOrigen(movAjustar);

		MovEntrada movDevolucionNuevo = new MovEntrada();

		movDevolucionNuevo.setFechaMovEntrada(movAjustar.getFechaIngreso());
		movDevolucionNuevo.setObservaciones(movAjustar.getObservaciones());
		movDevolucionNuevo.setTotalMovEntrada(movAjustar.getImporteTotal());
		movDevolucionNuevo.setActividad(act);
		movDevolucionNuevo.setMovOrigen(mo);
		movDevolucionNuevo.setSolicitud(s);
		movDevolucionNuevo.setDetalleME(detList);
		movDevolucionNuevo.setAlmacen(almRepository.get(movAjustar.getAlmacen()));

		movDevolucionNuevo.setUsuario(movAjustar.getUsuario());
		Date date = new Date();
		movDevolucionNuevo.setFechaCreado(date);

		meRepository.add(movDevolucionNuevo);

		return movDevolucionNuevo;

	}

	@Override
	public void crearMovAjustarSalida(MovSalidaDTO movAjustarFaltante) {
		// TODO Auto-generated method stub

		Float importetotal = (float) 0;

		List<DetalleMovSalida> detList;

		detList = new ArrayList<DetalleMovSalida>();

	

		for (DetMovSalidaDTO ddto : movAjustarFaltante.getDetalleMS()) {

			Lote lote = lRepository.get(ddto.getLote());

			for (LoteAlmacen la : lote.getLoteAlmacenes()) {

				if (la.getAlmacen().getCodAlmacen().equals(movAjustarFaltante.getAlmacen())) {

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
			
			if (lote.getStockActual() == 0) {

				ls.addHistorialEstadoLote(5, lote, movAjustarFaltante.getUsuario());

			} else if (lote.getStockActual() > 0) {

				ls.addHistorialEstadoLote(2, lote, movAjustarFaltante.getUsuario());

			}

		}
		
		movAjustarFaltante.setImporteTotal(importetotal);

		addMovSalida(movAjustarFaltante, detList);
		
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

		detSRepository.add(dms);

		return dms;
	}
	
	public MovSalida addMovSalida(MovSalidaDTO movSalida, List<DetalleMovSalida> detList) {

		MovOrigen mo = mos.addMovOrigen(movSalida, 6);

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

	@Override
	public void crearMovAjustarDesajuste(LoteDTO loteDTO, String usuario) {
		// TODO Auto-generated method stub
		
		Lote lote = lRepository.get(loteDTO.getCodLote());
		
		if (lote.getStockActual() == 0) {

			ls.addHistorialEstadoLote(5, lote, usuario);

		} else if (lote.getStockActual() > 0) {

			ls.addHistorialEstadoLote(2, lote, usuario);

		}		
		
		
	}

}
