package com.articulo.modules;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.application.exceptions.ValidationError;
import com.articulo.dto.ArticuloDTO;
import com.articulo.repository.*;
import com.conversion.repository.UnidadMedidaRepository;
import com.institucional.module.PParcialService;

/**
 * Proporciona las validaciones necesarias que se pueden presentar para los
 * diversos negocios relacionados con los articulos.
 * 
 * @author Gisella
 * 
 */

@Stateless
@LocalBean
public class ArticuloServiceValidations {
	@EJB
	ArticuloRepository articuloRepository;
	@EJB
	MaterialRepository materialRepository;
	@EJB
	TipoArticuloRepository tipoArticuloRepository;
	@EJB
	UnidadMedidaRepository unidadRepository;
	@EJB
	PParcialService partidaParcial;
	@EJB
	ArticuloServicesNew articulo;

	/**
	 * Valida añadir un articulo nuevo.
	 * 
	 */
	public List<ValidationError> validarAddArticulo(ArticuloDTO art) {
		List<ValidationError> errors = new ArrayList<ValidationError>();

		// validaciones respecto al material.
		if (materialRepository.get(art.getMaterialId()) == null) {
			errors.add(new ValidationError("Material", "Material inexistente"));
			System.out.println("material inexistente");
		}

		System.out.println(art.getNomArticulo().length());

		if (art.getNomArticulo().length() > 40 && art.getNomArticulo() == null) {

			errors.add(new ValidationError("Articulo",
					"El Nombre del Articulo supera los 40 caracteres"));

		}
		
		System.out.println(art.getDescArticulo().length());
		
		if (art.getDescArticulo().length() > 510
				|| art.getDescArticulo() == null) {

			errors.add(new ValidationError("Descripción",
					"La Descripción del Articulo no puede ser nula o superar los 500 caracteres, caracteres = "+ art.getDescArticulo().length()));

		}else if (art.getDescArticulo() != null) {
			if (articulo.getDesArticulo(art.getDescArticulo()) != null) {
				errors.add(new ValidationError("Descripción",
						"La Descripción del articulo ya existe en la base de datos"));
			}
		}
		System.out.println(art.getDescArticulo().length());
		
		if (art.getMaterialId() == null) {
			errors.add(new ValidationError("Material", "Material no asignado"));
		}
		// validacion respecto al tipo de articulo.
		if (tipoArticuloRepository.get(art.getTipoArticuloId()) == null) {
			errors.add(new ValidationError("TipoArticulo",
					"Tipo de articulo inexistente"));
		}
		if (art.getTipoArticuloId() == null) {
			errors.add(new ValidationError("TipoArticulo",
					"Tipo de articulo no asignaod"));
		}
		// validacion respecto a la Unidad.
		if (unidadRepository.get(art.getPesoUId()) == null) {
			errors.add(new ValidationError("Unidad", "Unidad inexistente"));
		}
		if (art.getPesoUId() == null) {
			errors.add(new ValidationError("Unidad", "Unidad no asignada"));
		}
		// Validacion PartidaParcial
		if (partidaParcial.getPPar(art.getPartidaParcialId()) == null) {
			errors.add(new ValidationError("PartidaParcial",
					"PartidaParcial inexistente"));
		}
		if (art.getPartidaParcialId() == null) {
			errors.add(new ValidationError("PartidaParcial",
					"Partida Parcial no asignada"));
		}

		if (art.getCodClase() != null) {

			if (!art.getCodClase().matches("\\d*")) {
				errors.add(new ValidationError("nroClase",
						"Escriba un Código de Clase valido Ejemplo: 9874"));
			}
		}
		if (art.getCodClase().length() > 5 && art.getCodClase() == null) {

			errors.add(new ValidationError("Clase",
					"El Código de clase supera los 5 caracteres"));

		}

		if (art.getCodItem() != null) {

			if (!art.getCodClase().matches("\\d*")) {
				errors.add(new ValidationError("nroItem",
						"Escriba un Código de Item valido Ejemplo: 0001"));
			}
		}
		if (art.getCodItem().length() > 5 && art.getCodItem() == null) {

			errors.add(new ValidationError("Item",
					"El Código de Item supera los 5 caracteres"));

		}

		return errors;
	}
}
