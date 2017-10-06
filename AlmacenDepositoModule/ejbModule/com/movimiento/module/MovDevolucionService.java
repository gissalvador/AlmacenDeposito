package com.movimiento.module;

import java.util.ArrayList;
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
import com.movimiento.dto.DetMovEntradaDTO;
import com.movimiento.dto.MovEntradaDTO;
import com.movimiento.dto.MovSalidaDTO;
import com.movimiento.dto.MovSalidaFactory;
import com.movimiento.entities.DetalleMovEntrada;
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
 * Session Bean implementation class MovDevolucionService
 */
@Stateless
@LocalBean
public class MovDevolucionService implements MovDevolucionServiceRemote {

	@EJB
	private MovEntradaRepository meRepository;

	@EJB
	private MovSalidaRepository msRepository;

	@EJB
	private LoteRepository lRepository;

	@EJB
	private LoteService ls;

	@EJB
	private DetalleMERepository detRepository;

	@EJB
	private ActividadService aService;

	@EJB
	private AlmacenRepository almRepository;

	@EJB
	private MovOrigenService mos;

	@EJB
	private SolicitudService ss;

	@EJB
	private MovEntradaValidations validator;


	/**
	 * Default constructor.
	 */
	public MovDevolucionService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public MovSalidaDTO buscarMovSalida(String codMovSalida) {
		// TODO Auto-generated method stub

		MovSalida movSalida = msRepository.get(codMovSalida);

		if (movSalida == null) {
			throw new BusinessException("Errores: el Movimiento de Salida no existe.");
		}

		if (movSalida.getMovOrigen().getTipoMovOrigen().getCodTMovOrigen() != 2) {

			throw new BusinessException("Errores: el Movimiento de Salida debe ser del tipo \"Consumo\" y es del tipo: "
					+ movSalida.getMovOrigen().getTipoMovOrigen().getNomTMovOrigen());

		}

		MovSalidaDTO movSalidaDTO = MovSalidaFactory.getMovSalidaDTO(movSalida);

		return movSalidaDTO;

	}

	@Override
	public void crearMovDevolucion(MovEntradaDTO movDevolucion) {
		// TODO Auto-generated method stub

		List<ValidationError> errors = validator.validaraddMovEntrada(movDevolucion);

		if (errors.size() > 0) {
			throw new BusinessException("Errores al agregar el Movimiento de Devolución.", errors);
		}

		Float importetotal = (float) 0;

		List<DetalleMovEntrada> detList;
		detList = new ArrayList<DetalleMovEntrada>();

		for (DetMovEntradaDTO ddto : movDevolucion.getListDetalleDTO()) {

			Lote l = lRepository.get(ddto.getLote());

			ls.addLoteAlmacen(movDevolucion.getAlmacen(), l, ddto.getCantidad(), null);

			DetalleMovEntrada dme = addDetalleMovEntrada(ddto);

			dme.setLote(l);

			importetotal += dme.getCantidadME() * dme.getImporteUnitarioME();

			detList.add(dme);

			ls.addHistorialEstadoLote(2, l, movDevolucion.getUsuario());

		}

		movDevolucion.setImporteTotal(importetotal);
		MovEntrada movDevolucionNuevo = addMovEntrada(movDevolucion, detList);
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

	public MovEntrada addMovEntrada(MovEntradaDTO movDevolucion, List<DetalleMovEntrada> detList) {

		Actividad act = aService.getActividad(movDevolucion.getActividad());

		Solicitud s = ss.addSolicitud(movDevolucion);

		MovOrigen mo = mos.addMovOrigen(movDevolucion);

		MovEntrada movDevolucionNuevo = new MovEntrada();

		movDevolucionNuevo.setFechaMovEntrada(movDevolucion.getFechaIngreso());
		movDevolucionNuevo.setObservaciones(movDevolucion.getObservaciones());
		movDevolucionNuevo.setTotalMovEntrada(movDevolucion.getImporteTotal());
		movDevolucionNuevo.setActividad(act);
		movDevolucionNuevo.setMovOrigen(mo);
		movDevolucionNuevo.setSolicitud(s);
		movDevolucionNuevo.setDetalleME(detList);
		movDevolucionNuevo.setAlmacen(almRepository.get(movDevolucion.getAlmacen()));

		movDevolucionNuevo.setUsuario(movDevolucion.getUsuario());
		Date date = new Date();
		movDevolucionNuevo.setFechaCreado(date);

		meRepository.add(movDevolucionNuevo);

		return movDevolucionNuevo;

	}

}
