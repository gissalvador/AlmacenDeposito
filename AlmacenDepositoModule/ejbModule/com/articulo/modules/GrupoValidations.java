package com.articulo.modules;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.application.exceptions.ValidationError;
import com.articulo.dto.GrupoDTO;
import com.articulo.entities.Grupo;
import com.articulo.repository.GrupoRepository;

/**
 * Session Bean implementation class GrupoValidations
 */
@Stateless
@LocalBean
public class GrupoValidations {

	@EJB
	GrupoRepository gRepository;

	@EJB
	GrupoService grupo;

	/**
	 * Default constructor.
	 */
	public GrupoValidations() {
		// TODO Auto-generated constructor stub
	}

	public List<ValidationError> validarAddGrupo(GrupoDTO nuevoGrupo) {
		List<ValidationError> errors = new ArrayList<ValidationError>();

		if (nuevoGrupo.getCodGrupo() != null) {
			Grupo grupo = gRepository.get(nuevoGrupo.getCodGrupo());
			if (grupo != null) {
				errors.add(new ValidationError("id",
						"El Grupo ya se encuentra registrado."));
			}
		}

		if (nuevoGrupo.getNomGrupo() == null) {
			errors.add(new ValidationError("Grupo",
					"El Nombre del Grupo no ha sido asignado"));
		}
		if(nuevoGrupo.getNomGrupo().length() > 40 && nuevoGrupo.getNomGrupo() == null){
			
			errors.add(new ValidationError("Grupo", "El Nombre del Grupo no puede superar los 40 caracteres"));
			
		}

		if (nuevoGrupo.getNomGrupo() != null) {
			if (grupo.getnomGrupo(nuevoGrupo.getNomGrupo()) != null) {
				errors.add(new ValidationError("Grupo",
						"El nombre del grupo ya existe en el la Base de Datos"));
			}
		}
		if(nuevoGrupo.getDesGrupo().length() > 250 && nuevoGrupo.getDesGrupo() == null){
			
			errors.add(new ValidationError("Grupo", "La Descripción del Grupo no puede superar los 250 caracteres o ser nula"));
			
		}

		
		return errors;

	}
}
