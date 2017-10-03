package com.institucional.module;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.application.exceptions.ValidationError;
import com.institucional.dto.ProveedorDTO;
import com.institucional.entities.Proveedor;
import com.institucional.repository.ProveedorRepository;
import com.institucional.repository.CentroRepository;

/**
 * Session Bean implementation class ProveedorValidations
 */
@Stateless
@LocalBean
public class ProveedorValidations {

	@EJB
	ProveedorRepository aRepository;

	@EJB
	CentroRepository cRepository;

	@EJB
	ProveedorService proveedor;

	/**
	 * Default constructor.
	 */
	public ProveedorValidations() {
		// TODO Auto-generated constructor stub
	}

	public List<ValidationError> validarAddProveedor(ProveedorDTO nuevoProveedor) {
		List<ValidationError> errors = new ArrayList<ValidationError>();

		if (nuevoProveedor.getCodPersona() != null) {
			Proveedor proveedor = aRepository.get(nuevoProveedor
					.getCodPersona());
			if (proveedor != null) {
				errors.add(new ValidationError("id",
						"El Proveedor ya se encuentra registrado."));
			}
		}

		if (nuevoProveedor.getRazonSocial() == null
				|| nuevoProveedor.getRazonSocial().length() <= 0) {
			errors.add(new ValidationError("RazonSocial",
					"La Razon Social no ha sido asignado"));
		}

		if (nuevoProveedor.getRazonSocial().length() > 250) {

			errors.add(new ValidationError("RazonSocial",
					"La Razon Social supera los 250 caracteres"));

		}

		if (nuevoProveedor.getAlias() == null
				|| nuevoProveedor.getAlias().length() <= 0) {
			errors.add(new ValidationError("Alias",
					"El Alias no ha sido asignado"));
		}
		if (nuevoProveedor.getRazonSocial().length() > 100) {

			errors.add(new ValidationError("Alias",
					"El Alias supera los 100 caracteres"));

		}

		if (nuevoProveedor.getCuit() == null
				|| nuevoProveedor.getCuit().length() <= 0) {
			errors.add(new ValidationError("CUIT",
					"El CUIT no ha sido asignado"));
		} else if (!nuevoProveedor.getCuit().matches("\\d*")
				|| nuevoProveedor.getCuit().length() != 11) {

			errors.add(new ValidationError("CUIT",
					"Escriba un C.U.I.T. valido Ejemplo: 30546660210"));
		}

		if (nuevoProveedor.getCuit() != null
				&& nuevoProveedor.getCuit() != null) {
			if (proveedor.getCUIT(nuevoProveedor.getCuit()) != null) {
				errors.add(new ValidationError("CUIT",
						"El CUIT del proveedor ya existe en la base de datos"));
			}
		}

		if (nuevoProveedor.getTelefono() == null
				|| nuevoProveedor.getTelefono().length() <= 0) {
			errors.add(new ValidationError("Telefono",
					"El Teléfono no ha sido asignado"));
		}

		if (nuevoProveedor.getTelefono().length() > 25) {

			errors.add(new ValidationError("Telefono",
					"El Teléfono supera los 25 caracteres"));
		}

		if (nuevoProveedor.getNroProveedor() != null) {

			if (!nuevoProveedor.getNroProveedor().matches("\\d*")) {
				errors.add(new ValidationError("nroProveedor",
						"Escriba un Nro. de proveedor valido Ejemplo: 290387"));
			}
		}

		if (nuevoProveedor.getCelular().length() > 25) {

			errors.add(new ValidationError("Celular",
					"El Celular supera los 25 caracteres"));
		}

		if (nuevoProveedor.getDomicilio().length() > 250) {

			errors.add(new ValidationError("Domicilio",
					"El Domicilio supera los 250 caracteres"));
		}

		if (nuevoProveedor.getMail().length() > 250) {

			errors.add(new ValidationError("Mail",
					"El Mail supera los 250 caracteres"));
		}
		return errors;

	}

}
