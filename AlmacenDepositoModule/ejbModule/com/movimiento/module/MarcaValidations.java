package com.movimiento.module;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.application.exceptions.ValidationError;
import com.institucional.repository.CentroRepository;
import com.movimiento.dto.MarcaDTO;
import com.movimiento.entities.Marca;
import com.movimiento.repository.MarcaRepository;

/**
 * Session Bean implementation class MarcaValidations
 */
@Stateless
@LocalBean
public class MarcaValidations {
	
	
	@EJB
	MarcaRepository aRepository;

	@EJB
	CentroRepository cRepository;

	@EJB
	MarcaService marca;

    /**
     * Default constructor. 
     */
    public MarcaValidations() {
        // TODO Auto-generated constructor stub
    }
    
    public List<ValidationError> validarAddMarca(MarcaDTO nuevoMarca) {
		List<ValidationError> errors = new ArrayList<ValidationError>();

		if (nuevoMarca.getCodMarca() != null) {
			Marca marca = aRepository.get(nuevoMarca.getCodMarca());
			if (marca != null) {
				errors.add(new ValidationError("id",
						"La Marca ya se encuentra registrado."));
			}
		}

		if (nuevoMarca.getNomMarca() == null) {
			errors.add(new ValidationError("Marca", "El Nombre del Marca no ha sido asignado"));
		}
		
		if (nuevoMarca.getNomMarca().length() > 100) {

			errors.add(new ValidationError("Marca",
					"El Nombre de la Marca supera los 100 caracteres"));
		}

		if (nuevoMarca.getNomMarca()!= null ){
		if (marca.getnomMarca(nuevoMarca.getNomMarca()) != null) {
			errors.add(new ValidationError("Marca",
					"El nombre del marca ya existe en la Base de Datos"));
		}
		}

		return errors;

	}

}
