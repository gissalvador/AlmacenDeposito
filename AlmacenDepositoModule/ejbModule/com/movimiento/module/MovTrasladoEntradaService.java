package com.movimiento.module;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.application.exceptions.BusinessException;
import com.institucional.dto.AlmacenDTO;
import com.institucional.dto.AlmacenFactory;
import com.institucional.entities.Actividad;
import com.institucional.entities.Almacen;
import com.institucional.module.ActividadService;
import com.institucional.module.AlmacenService;
import com.institucional.repository.AlmacenRepository;
import com.movimiento.dto.DetMovEntradaDTO;
import com.movimiento.dto.MovEntradaDTO;
import com.movimiento.dto.MovSalidaDTO;
import com.movimiento.dto.MovSalidaFactory;
import com.movimiento.entities.DetalleMovEntrada;
import com.movimiento.entities.HistorialEstadoLote;
import com.movimiento.entities.Lote;
import com.movimiento.entities.MovEntrada;
import com.movimiento.entities.MovOrigen;
import com.movimiento.entities.MovSalida;
import com.movimiento.entities.Solicitud;
import com.movimiento.repository.DetalleMERepository;
import com.movimiento.repository.LoteRepository;
import com.movimiento.repository.MovEntradaRepository;
import com.movimiento.repository.MovSalidaRepository;

/**
 * Session Bean implementation class MovTrasladoEntradaService
 */
@Stateless
@LocalBean
public class MovTrasladoEntradaService implements MovTrasladoEntradaServiceRemote {

	@EJB
	private MovEntradaRepository meRepository;

	@EJB
	private MovSalidaRepository msRepository;

	@EJB
	private AlmacenService almservice;

	@EJB
	private AlmacenRepository almRepository;

	@EJB
	private LoteRepository lRepository;

	@EJB
	private LoteService ls;

	@EJB
	private DetalleMERepository detRepository;

	@EJB
	private ActividadService aService;

	@EJB
	private MovOrigenService mos;

	@EJB
	private SolicitudService ss;

	@EJB
	private MovEntradaValidations validator;

	/**
	 * Default constructor.
	 */
	public MovTrasladoEntradaService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public MovSalidaDTO buscarMovSalida(String nroComprobante) {
		// TODO Auto-generated method stub
		MovSalida movSalida = msRepository.get(nroComprobante);

		if (movSalida == null) {
			throw new BusinessException("Errores: el Movimiento de Salida no existe.");
		}

		if (movSalida.getMovOrigen().getTipoMovOrigen().getCodTMovOrigen() != 7) {

			throw new BusinessException(
					"Errores: el Movimiento de Salida debe ser del tipo \"Traslado Salida\" y es del tipo: "
							+ movSalida.getMovOrigen().getTipoMovOrigen().getNomTMovOrigen());

		}

		MovSalidaDTO movSalidaDTO = MovSalidaFactory.getMovSalidaDTO(movSalida);

		return movSalidaDTO;

	}

	@Override
	public AlmacenDTO buscarAlmacen(String nroComprobante) {
		// TODO Auto-generated method stub

		return AlmacenFactory.getAlmacenDTO(almservice.getAlmacen(nroComprobante));
	}

	@Override
	public void crearMovTrasladoEntrada(MovEntradaDTO movTrasladoEntrada) {
		// TODO Auto-generated method stub

		Float importetotal = (float) 0;

		List<DetalleMovEntrada> detList;
		detList = new ArrayList<DetalleMovEntrada>();

		for (DetMovEntradaDTO ddto : movTrasladoEntrada.getListDetalleDTO()) {

			Lote l = lRepository.get(ddto.getLote());

			ls.addLoteAlmacen(movTrasladoEntrada.getAlmacen(), l, ddto.getCantidad(), ddto.getUbicacion());

			l.setStockActual(l.getStockActual() + ddto.getCantidad());

			DetalleMovEntrada dme = addDetalleMovEntrada(ddto);

			dme.setLote(l);

			importetotal += dme.getCantidadME() * dme.getImporteUnitarioME();

			detList.add(dme);

			if (ddto.isAjustar()) {

				ls.addHistorialEstadoLote(3, l, movTrasladoEntrada.getUsuario());

			} else {

				int index = l.getHistorialEstadoLote().size() - 2;

				// System.out.println(index);

				// HistorialEstadoLote hel =
				// l.getHistorialEstadoLote().get(index);

				// System.out.println(l.getHistorialEstadoLote().get(index).getEstadoLote().getNomEstadoLote());

				ls.addHistorialEstadoLote(l.getHistorialEstadoLote().get(index).getEstadoLote().getCodEstadoLote(), l,
						movTrasladoEntrada.getUsuario());

			}

		}

		movTrasladoEntrada.setImporteTotal(importetotal);
		MovEntrada movDevolucionNuevo = addMovEntrada(movTrasladoEntrada, detList);
		System.out.println(movDevolucionNuevo.getCodMovEntrada());

	}

	public DetalleMovEntrada addDetalleMovEntrada(DetMovEntradaDTO ddto) {

		DetalleMovEntrada dme = new DetalleMovEntrada();

		dme.setCantidadME(ddto.getCantidad());
		dme.setImporteUnitarioME(ddto.getImporteUnitario());
		dme.setNroSerie_Proveedor(ddto.getNroSerie_Proveedor());

		detRepository.add(dme);

		return dme;
	}

	public MovEntrada addMovEntrada(MovEntradaDTO movTrasladoEntrada, List<DetalleMovEntrada> detList) {

		Actividad act = aService.getActividad(movTrasladoEntrada.getActividad());

		Solicitud s = ss.addSolicitud(movTrasladoEntrada);

		MovOrigen mo = mos.addMovOrigen(movTrasladoEntrada);

		MovEntrada movTrasladoEntradaNuevo = new MovEntrada();

		movTrasladoEntradaNuevo.setFechaMovEntrada(movTrasladoEntrada.getFechaIngreso());
		movTrasladoEntradaNuevo.setObservaciones(movTrasladoEntrada.getObservaciones());
		movTrasladoEntradaNuevo.setTotalMovEntrada(movTrasladoEntrada.getImporteTotal());
		movTrasladoEntradaNuevo.setActividad(act);
		movTrasladoEntradaNuevo.setMovOrigen(mo);
		movTrasladoEntradaNuevo.setSolicitud(s);
		movTrasladoEntradaNuevo.setDetalleME(detList);
		movTrasladoEntradaNuevo.setAlmacen(almRepository.get(movTrasladoEntrada.getAlmacen()));

		movTrasladoEntradaNuevo.setUsuario(movTrasladoEntrada.getUsuario());
		Date date = new Date();
		movTrasladoEntradaNuevo.setFechaCreado(date);

		meRepository.add(movTrasladoEntradaNuevo);

		return movTrasladoEntradaNuevo;

	}

}
