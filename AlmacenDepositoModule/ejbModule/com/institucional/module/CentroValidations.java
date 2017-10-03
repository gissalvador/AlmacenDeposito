package com.institucional.module;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.application.exceptions.ValidationError;
import com.institucional.dto.CentroDTO;
import com.institucional.entities.Centro;
import com.institucional.repository.ActividadRepository;
import com.institucional.repository.CentroRepository;

/**
 * Session Bean implementation class CentroValidations
 */
@Stateless
@LocalBean
public class CentroValidations {

	@EJB
	CentroRepository cRepository;

	@EJB
	ActividadRepository actRepository;

	@EJB
	CentroService centro;

	/**
	 * Default constructor.
	 */
	public CentroValidations() {
		// TODO Auto-generated constructor stub
	}

	public List<ValidationError> validarAddCentro(CentroDTO nuevoCentro) {
		List<ValidationError> errors = new ArrayList<ValidationError>();

		if (nuevoCentro.getCodCentro() != null) {
			Centro centro = cRepository.get(nuevoCentro.getCodCentro());
			if (centro != null) {
				errors.add(new ValidationError("id",
						"El Centro ya se encuentra registrado."));
			}
		}

		if (nuevoCentro.getNomCentro() == null) {

			errors.add(new ValidationError("Centro",
					"El nombre del centro no ha sido asignado"));

		}
		if (nuevoCentro.getNomCentro() != null) {
			if (centro.getnomCentro(nuevoCentro.getNomCentro()) != null) {
				errors.add(new ValidationError("Centro",
						"El nombre del centro ya existe en la base de datos"));
			}
		}
		if (nuevoCentro.getNomCentro().length() > 50) {

			errors.add(new ValidationError("Centro",
					"El nombre del centro supera los 50 caracteres"));

		}

		return errors;

	}

}
