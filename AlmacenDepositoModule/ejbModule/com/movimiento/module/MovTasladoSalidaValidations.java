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
import com.movimiento.dto.MovSalidaDTO;
import com.movimiento.entities.MovEntrada;
import com.movimiento.repository.MovEntradaRepository;
import com.movimiento.repository.TipoMORepository;

/**
 * Session Bean implementation class MovEntradaValidations
 */
@Stateless
@LocalBean
public class MovTasladoSalidaValidations {

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

	public MovTasladoSalidaValidations() {
		// TODO Auto-generated constructor stub
	}

	public List<ValidationError> validaraddMovSalida(
			MovSalidaDTO nuevoMovSalida) {
		List<ValidationError> errors = new ArrayList<ValidationError>();

		if (nuevoMovSalida.getId() != null) {
			MovEntrada movEntrada = meRepository.get(nuevoMovSalida.getId());
			if (movEntrada != null) {
				errors.add(new ValidationError("id",
						"El Movimiento de Entrada ya se encuentra registrado."));
			}
		}

		
		if (nuevoMovSalida.getAlmacen() == null
				|| nuevoMovSalida.getAlmacen() <= 0) {
			errors.add(new ValidationError("almacen",
					"Debe definir un numero de almacen."));
		}

		if (almRepository.get(nuevoMovSalida.getAlmacen()) == null) {
			errors.add(new ValidationError("almacen",
					"El Almacen no se encuentra registrado."));
		}

		if (nuevoMovSalida.getFechaSalida() == null) {
			errors.add(new ValidationError("fechaSalida",
					"Debe definir una Fecha."));
		}

		if (nuevoMovSalida.getLegajo() == null
				|| nuevoMovSalida.getLegajo().length() <= 0) {
			errors.add(new ValidationError("legajo",
					"Debe definir un numero de legajo."));
		}
		
		if (eService.getEmpleado(nuevoMovSalida.getLegajo()) == null) {
			errors.add(new ValidationError("legajo",
					"El Número de Legajo no se encuentra regitrado"));
		}
			
		if (nuevoMovSalida.getObservaciones().length() > 250) {

			errors.add(new ValidationError("Observaciones",
					"Las observaciones superan los 250 caracteres"));
		}

		return errors;

	}
}
