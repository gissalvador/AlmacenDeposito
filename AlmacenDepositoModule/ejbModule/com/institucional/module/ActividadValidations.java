package com.institucional.module;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.application.exceptions.ValidationError;
import com.institucional.dto.ActividadDTO;
import com.institucional.entities.Actividad;
import com.institucional.repository.ActividadRepository;
import com.institucional.repository.EmpleadoRepository;

/**
 * Session Bean implementation class ActividadValidation
 */
@Stateless
@LocalBean
public class ActividadValidations {

	/**
	 * Default constructor.
	 */

	@EJB
	ActividadRepository aRepository;

	@EJB
	EmpleadoRepository eRepository;

	@EJB
	ActividadService actividad;
	@EJB
	private EmpleadoService eService;

	public ActividadValidations() {
		// TODO Auto-generated constructor stub
	}

	public List<ValidationError> validarAddActividad(ActividadDTO nuevoActividad) {
		List<ValidationError> errors = new ArrayList<ValidationError>();

		if (nuevoActividad.getCodActividad() != null) {
			Actividad actividad = aRepository.get(nuevoActividad
					.getCodActividad());
			if (actividad != null) {
				errors.add(new ValidationError("id",
						"El Actividad ya se encuentra registrado."));
			}
		}

		if (nuevoActividad.getNomActividad() == null
				|| nuevoActividad.getNomActividad().length() <= 0) {
			errors.add(new ValidationError("Actividad",
					"El Nombre del Actividad no ha sido asignado"));
		}

		if (nuevoActividad.getNomActividad().length() > 99) {
			errors.add(new ValidationError("Actividad",
					"El Nombre del Actividad no puede superar los 100 caracteres"));
		}

		if (nuevoActividad.getNroActividad() != null) {
			if (actividad.getNroActividad(nuevoActividad.getNroActividad()) != null) {
				errors.add(new ValidationError("Actividad",
						"El número de actividad ya existe en la base de datos"));
			}
		}

		if (nuevoActividad.getRedProgramatica() == null
				|| nuevoActividad.getRedProgramatica().length() <= 0) {
			errors.add(new ValidationError("RedProgramatica",
					"Debe definir un numero de Red Programatica."));
		}
		// validaciones respecto al Empleado.

		if (nuevoActividad.getNroRes() == null) {
			errors.add(new ValidationError("nroRes",
					"Legajo de Responsable no asignado"));
		}

		if (eService.getEmpleado(nuevoActividad.getNroRes()) == null) {
			errors.add(new ValidationError("nroRes",
					"El Número de Legajo del Responsable no se encuentra regitrado"));
		}

		if (nuevoActividad.getNroSubRes() == null) {
			errors.add(new ValidationError("nroSubRes",
					"Legajo de Subresponsable no asignado"));
		}

		if (eService.getEmpleado(nuevoActividad.getNroSubRes()) == null) {
			errors.add(new ValidationError("nroSubRes",
					"El Número de Legajo del Subresponsable no se encuentra regitrado"));
		}

		if (nuevoActividad.getRedProgramatica().length() > 250) {
			errors.add(new ValidationError("Actividad",
					"La Red Programatica no puede superar los 250 caracteres"));
		}

		return errors;

	}

}
