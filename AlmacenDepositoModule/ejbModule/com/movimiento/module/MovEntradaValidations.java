package com.movimiento.module;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.application.exceptions.ValidationError;
import com.institucional.module.ActividadService;
import com.institucional.module.EmpleadoService;
import com.institucional.module.ProveedorService;
import com.institucional.repository.ActividadRepository;
import com.institucional.repository.AlmacenRepository;
import com.movimiento.dto.MovEntradaDTO;
import com.movimiento.entities.MovEntrada;
import com.movimiento.repository.MovEntradaRepository;
import com.movimiento.repository.TipoMORepository;

/**
 * Session Bean implementation class MovEntradaValidations
 */
@Stateless
@LocalBean
public class MovEntradaValidations {

	@EJB
	MovEntradaRepository meRepository;

	@EJB
	ActividadRepository actRepository;

	@EJB
	AlmacenRepository almRepository;

	@EJB
	TipoMORepository tmoRepository;

	@EJB
	private ProveedorService pService;

	@EJB
	private EmpleadoService eService;

	@EJB
	private ActividadService aService;

	/**
	 * Default constructor.
	 */

	public MovEntradaValidations() {
		// TODO Auto-generated constructor stub
	}

	public List<ValidationError> validaraddMovEntrada(
			MovEntradaDTO nuevoMovEntrada) {
		List<ValidationError> errors = new ArrayList<ValidationError>();

		if (nuevoMovEntrada.getId() != null) {
			MovEntrada movEntrada = meRepository.get(nuevoMovEntrada.getId());
			if (movEntrada != null) {
				errors.add(new ValidationError("id",
						"El Movimiento de Entrada ya se encuentra registrado."));
			}
		}

		if (nuevoMovEntrada.getActividad() == null
				|| nuevoMovEntrada.getActividad() <= 0) {
			errors.add(new ValidationError("actividad",
					"Debe definir un numero de actividad."));
		}

		if (aService.getActividad(nuevoMovEntrada.getActividad()) == null) {
			errors.add(new ValidationError("actividad",
					"La Actividad no se encuentra registrada."));
		}

		if (nuevoMovEntrada.getAlmacen() == null
				|| nuevoMovEntrada.getAlmacen() <= 0) {
			errors.add(new ValidationError("almacen",
					"Debe definir un numero de almacen."));
		}

		if (almRepository.get(nuevoMovEntrada.getAlmacen()) == null) {
			errors.add(new ValidationError("almacen",
					"El Almacen no se encuentra registrado."));
		}

		if (nuevoMovEntrada.getFechaIngreso() == null) {
			errors.add(new ValidationError("fechaIngreso",
					"Debe definir una Fecha."));
		}

		if (nuevoMovEntrada.getComprobante() == null
				|| nuevoMovEntrada.getComprobante() <= 0) {
			errors.add(new ValidationError("comprobante",
					"Debe definir un numero de Tipo de Comprobante."));
		}

		if (tmoRepository.get(nuevoMovEntrada.getComprobante()) == null) {
			errors.add(new ValidationError("comprobante",
					"El Tipo Movimiento de Origen no se encuentra registrado."));
		} else if (tmoRepository.get(nuevoMovEntrada.getComprobante())
				.getTipoPersona().getCodTipoPersona() == 1) {

			if (eService.getEmpleado(nuevoMovEntrada.getCuitlegajo()) == null) {
				errors.add(new ValidationError("cuitlegajo",
						"El Número de Legajo no se encuentra regitrado"));
			}

		} else if (pService.getProveedor(nuevoMovEntrada.getCuitlegajo()) == null) {
			errors.add(new ValidationError("cuitlegajo",
					"El Número de CUIT no se encuentra regitrado"));
		}

		if (nuevoMovEntrada.getComprobante() != 3) {
			if (nuevoMovEntrada.getNroComprobante() == null
					|| nuevoMovEntrada.getNroComprobante().length() <= 0) {
				errors.add(new ValidationError("Numero de Comprobante",
						"Debe definir un número de Comprobante."));
			}
		}

		if (nuevoMovEntrada.getFechaMO() == null) {
			errors.add(new ValidationError("fechaMO",
					"Debe definir Fecha de Movimiento de Origen."));
		}

		if (nuevoMovEntrada.getNroSolicitud() == null
				|| nuevoMovEntrada.getNroSolicitud() <= 0) {
			errors.add(new ValidationError("nroSolicitud",
					"Debe definir un numero de solicitud."));
		}

		if (nuevoMovEntrada.getCuitlegajo() == null
				|| nuevoMovEntrada.getCuitlegajo().length() <= 0) {
			errors.add(new ValidationError("cuitlegajo",
					"Debe definir un numero de cuit o de legajo."));
		}

		if (nuevoMovEntrada.getComprobante() == null
				|| nuevoMovEntrada.getComprobante() <= 0) {
			errors.add(new ValidationError("comprobante",
					"Debe definir un numero de Tipo de Comprobante."));

		}

		if (nuevoMovEntrada.getObservaciones().length() > 250) {

			errors.add(new ValidationError("Observaciones",
					"Las observaciones superan los 250 caracteres"));
		}

		return errors;

	}
}
