package com.movimiento.module;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.application.exceptions.ValidationError;
import com.movimiento.dto.ModeloDTO;
import com.movimiento.entities.Modelo;
import com.movimiento.repository.MarcaRepository;
import com.movimiento.repository.ModeloRepository;

/**
 * Session Bean implementation class ModeloValidations
 */
@Stateless
@LocalBean
public class ModeloValidations {

	@EJB
	ModeloRepository mRepository;

	@EJB
	MarcaRepository marRepository;

	@EJB
	ModeloService mService;

	/**
	 * Default constructor.
	 */
	public ModeloValidations() {
		// TODO Auto-generated constructor stub
	}

	public List<ValidationError> validarAddModelo(ModeloDTO nuevoModelo) {
		List<ValidationError> errors = new ArrayList<ValidationError>();

		if (nuevoModelo.getCodModelo() != null) {
			Modelo modelo = mRepository.get(nuevoModelo.getCodModelo());
			if (modelo != null) {
				errors.add(new ValidationError("id",
						"El Modelo ya se encuentra registrado."));
			}
		}

		if (nuevoModelo.getNomModelo() == null) {
			errors.add(new ValidationError("Modelo",
					"El Nombre del Modelo no ha sido asignado"));
		}

		if (nuevoModelo.getNomModelo().length() > 250) {

			errors.add(new ValidationError("Modelo",
					"El Nombre del Modelo supera los 250 caracteres"));
		}

		// validaciones respecto al Marca.
		if (marRepository.get(nuevoModelo.getMarcaId()) == null) {
			errors.add(new ValidationError("Marca", "Marca inexistente"));
		}

		if (nuevoModelo.getMarcaId() == null) {
			errors.add(new ValidationError("Marca", "Marca no asignado"));
		}
		if (nuevoModelo.getNomModelo() != null
				&& nuevoModelo.getMarcaId() != null) {
			if (mService.getnomModelo(nuevoModelo.getNomModelo(),
					nuevoModelo.getMarcaId()) != null) {
				errors.add(new ValidationError("Modelo",
						"El modelo ya existe en la marca: "
								+ nuevoModelo.getMarca()));
			}
		}

		return errors;

	}

}
