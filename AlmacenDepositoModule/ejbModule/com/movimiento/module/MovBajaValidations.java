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
import com.movimiento.entities.Lote;
import com.movimiento.entities.MovEntrada;
import com.movimiento.repository.LoteRepository;
import com.movimiento.repository.MovEntradaRepository;
import com.movimiento.repository.TipoMORepository;

/**
 * Session Bean implementation class MovEntradaValidations
 */
@Stateless
@LocalBean
public class MovBajaValidations {

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
	
	@EJB
	private LoteRepository lRepository;

	/**
	 * Default constructor.
	 */

	public MovBajaValidations() {
		// TODO Auto-generated constructor stub
	}

	public List<ValidationError> validaraddMovBaja(
			MovSalidaDTO nuevoMovBaja, String codLote) {
		List<ValidationError> errors = new ArrayList<ValidationError>();

		if (nuevoMovBaja.getId() != null) {
			MovEntrada movEntrada = meRepository.get(nuevoMovBaja.getId());
			if (movEntrada != null) {
				errors.add(new ValidationError("id",
						"El Movimiento de Entrada ya se encuentra registrado."));
			}
		}

		
		if (nuevoMovBaja.getAlmacen() == null
				|| nuevoMovBaja.getAlmacen() <= 0) {
			errors.add(new ValidationError("almacen",
					"Debe definir un numero de almacen."));
		}

		if (almRepository.get(nuevoMovBaja.getAlmacen()) == null) {
			errors.add(new ValidationError("almacen",
					"El Almacen no se encuentra registrado."));
		}

		if (nuevoMovBaja.getFechaSalida() == null) {
			errors.add(new ValidationError("fechaSalida",
					"Debe definir una Fecha."));
		}
		
		Lote lote = lRepository.get(codLote);
						
		if (lote.getEstadoActualLote().getCodEstadoLote() != 5  &&  lote.getEstadoActualLote().getCodEstadoLote() != 6) {
			errors.add(new ValidationError("EstadoLote",
					"El lote debe encontrarse en estado \"Cerrado\" o \"Vencido\" para poder darse de Baja. Estado Actual: " + lote.getEstadoActualLote().getNomEstadoLote()));
		}
	
		if (nuevoMovBaja.getObservaciones().length() > 250) {

			errors.add(new ValidationError("Observaciones",
					"Las observaciones superan los 250 caracteres"));
		}

		return errors;

	}
}
