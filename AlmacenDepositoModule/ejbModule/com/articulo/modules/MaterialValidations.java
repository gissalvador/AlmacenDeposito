package com.articulo.modules;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.application.exceptions.ValidationError;
import com.articulo.dto.MaterialDTO;
import com.articulo.entities.Material;
import com.articulo.repository.MaterialRepository;



/**
 * Session Bean implementation class MaterialValidations
 */
@Stateless
@LocalBean
public class MaterialValidations {

	
	@EJB
	MaterialRepository mRepository;
	
	@EJB
	MaterialService material;

    /**
     * Default constructor. 
     */
    public MaterialValidations() {
        // TODO Auto-generated constructor stub
    }
    
    public List<ValidationError> validarAddMaterial(MaterialDTO nuevoMaterial) {
		List<ValidationError> errors = new ArrayList<ValidationError>();

		if (nuevoMaterial.getCodMaterial() != null) {
			Material material = mRepository.get(nuevoMaterial.getCodMaterial());
			if (material != null) {
				errors.add(new ValidationError("id",
						"El Material ya se encuentra registrado."));
			}
		}

		if (nuevoMaterial.getNomMaterial() == null) {
			errors.add(new ValidationError("Material", "El Nombre del Material no ha sido asignado"));
		}
		if (nuevoMaterial.getNomMaterial().length() > 100) {
			errors.add(new ValidationError("Material", "El Nombre del Material no puede superar los 100 caracteres"));
		}
		if (nuevoMaterial.getDesMaterial() == null) {
			errors.add(new ValidationError("Material", "La descripción del Material no ha sido asignado"));
		}
		if (nuevoMaterial.getNomMaterial().length() > 250) {
			errors.add(new ValidationError("Material", "La descripción del Material no puede superar los 250 caracteres"));
		}
		if (nuevoMaterial.getTipoMaterial() == null) {
			errors.add(new ValidationError("Material", "El Tipo del Material no ha sido asignado"));
		}
		if (nuevoMaterial.getTipoMaterial().length() > 100) {
			errors.add(new ValidationError("Material", "El Tipo del Material no puede superar los 100 caracteres"));
		}
		if (nuevoMaterial.getTipoMaterial() == null) {
			errors.add(new ValidationError("Material", "El Tipo del Material no ha sido asignado"));
		}
		if (nuevoMaterial.getNomMaterial()!= null){
		if (material.getnomMaterial(nuevoMaterial.getNomMaterial())  != null) {
			errors.add(new ValidationError("Material",
					"El nombre del material ya existe en la base de datos"));
		}
		}

		return errors;

	}


}
