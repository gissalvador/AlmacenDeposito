package com.movimiento.module;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.application.exceptions.ValidationError;
import com.institucional.repository.TipoPersonaRepository;
import com.movimiento.dto.TipoMovOrigenDTO;
import com.movimiento.entities.TipoMovOrigen;
import com.movimiento.repository.TipoMORepository;

/**
 * Session Bean implementation class TipoMovOrigenValidation
 */
@Stateless
@LocalBean
public class TipoMovOrigenValidation {


	@EJB
	TipoMORepository moRepository;

	@EJB
	TipoMovOrigenService tipoMovOrigen;
	
	@EJB
	private TipoPersonaRepository tpRepository;
    /**
     * Default constructor. 
     */
    public TipoMovOrigenValidation() {
        // TODO Auto-generated constructor stub
    }
    
    public List<ValidationError> validarAddTipoMovOrigen(TipoMovOrigenDTO nuevoTipoMovOrigen) {
		List<ValidationError> errors = new ArrayList<ValidationError>();

		if (nuevoTipoMovOrigen.getCodTMovOrigen() != null) {
			TipoMovOrigen movOrigen = moRepository.get(nuevoTipoMovOrigen.getCodTMovOrigen());
			if (movOrigen != null) {
				errors.add(new ValidationError("id",
						"El Tipo Movimiento Origen ya se encuentra registrado."));
			}
		}

		if (nuevoTipoMovOrigen.getNomTMovOrigen() == null) {
			errors.add(new ValidationError("TipoMovOrigen", "El Nombre del Tipo Movimiento Origen no ha sido asignado"));
		}
		
		
		if (nuevoTipoMovOrigen.getNomTMovOrigen()!= null){
		if (tipoMovOrigen.getnomTMovOrigen(nuevoTipoMovOrigen.getNomTMovOrigen())  != null){
			errors.add(new ValidationError("TipoMovOrigen",
					"El nombre del Tipo Movimiento Origen ya existe en la base de datos" ));
		}
		}
		
		// validaciones respecto al TipoPersona.
		if (tpRepository.get(nuevoTipoMovOrigen.getNroTipoPersona()) == null) {
			errors.add(new ValidationError("TipoPersona", "Tipo Persona inexistente"));
			System.out.println("material inexistente");
		}
		
		if (nuevoTipoMovOrigen.getNroTipoPersona() == null) {
			errors.add(new ValidationError("TipoPersona", "Tipo Persona no asignado"));
		}
		

		return errors;

	}


}
