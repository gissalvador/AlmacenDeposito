package com.institucional.module;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.application.exceptions.ValidationError;
import com.institucional.dto.AlmacenDTO;
import com.institucional.entities.Almacen;
import com.institucional.repository.AlmacenRepository;
import com.institucional.repository.CentroRepository;

/**
 * Session Bean implementation class AlmacenValidations
 */
@Stateless
@LocalBean
public class AlmacenValidations {

	@EJB
	AlmacenRepository aRepository;

	@EJB
	CentroRepository cRepository;

	@EJB
	AlmacenService almacen;

	/**
	 * Default constructor.
	 */
	public AlmacenValidations() {
		// TODO Auto-generated constructor stub
	}

	public List<ValidationError> validarAddAlmacen(AlmacenDTO nuevoAlmacen) {
		List<ValidationError> errors = new ArrayList<ValidationError>();

		if (nuevoAlmacen.getCodAlmacen() != null) {
			Almacen almacen = aRepository.get(nuevoAlmacen.getCodAlmacen());
			if (almacen != null) {
				errors.add(new ValidationError("id",
						"El Almacen ya se encuentra registrado."));
			}
		}

		if (nuevoAlmacen.getNomAlmacen() == null) {
			errors.add(new ValidationError("Almacen",
					"El Nombre del Almacen no ha sido asignado"));
		}
		if (nuevoAlmacen.getNomAlmacen().length() > 50) {

			errors.add(new ValidationError("Almacen",
					"El Nombre del Almacen supera los 50 caracteres"));

		}

		// validaciones respecto al Centro.
		if (cRepository.get(nuevoAlmacen.getCodCentro()) == null) {
			errors.add(new ValidationError("Centro", "Centro inexistente"));
			System.out.println("Centro inexistente");
		}

		if (nuevoAlmacen.getCodCentro() == null) {
			errors.add(new ValidationError("Centro", "Centro no asignado"));
		}
		if (nuevoAlmacen.getNomAlmacen() != null
				&& nuevoAlmacen.getCodCentro() != null) {
			if (almacen.getnomAlmacen(nuevoAlmacen.getNomAlmacen(),
					nuevoAlmacen.getCodCentro()) != null) {
				errors.add(new ValidationError("Almacen",
						"El nombre del almacen ya existe en el centro"
								+ nuevoAlmacen.getCodCentro()));
			}
		}

		return errors;

	}

}
