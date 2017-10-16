package com.institucional.module;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.clases.propias.CapitalizeString;
import com.institucional.dto.AlmacenDTO;
import com.institucional.dto.AlmacenFactory;
import com.institucional.entities.Almacen;
import com.institucional.repository.AlmacenRepository;
import com.institucional.repository.CentroRepository;


/**
 * Session Bean implementation class AlmacenService
 */
@Stateless
@LocalBean
public class AlmacenService implements AlmacenServiceRemote {

	@EJB
	private AlmacenRepository aRepository;

	@EJB
	private CentroRepository cRepository;

	@EJB
	private AlmacenValidations validator;

	/**
	 * Default constructor.
	 */
	public AlmacenService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<AlmacenDTO> listAll() {

		return AlmacenFactory.getAlmacenDTO(aRepository.getAll());
	}

	@Override
	public void addAlmacen(AlmacenDTO almacen) {
		List<ValidationError> errors = validator.validarAddAlmacen(almacen);
		if (errors.size() > 0) {
			throw new BusinessException("Errores al agregar el almacen", errors);
		}

		Almacen almacenNuevo = new Almacen();

		almacenNuevo.setNomAlmacen(CapitalizeString.capitalizeString(almacen.getNomAlmacen()));
		almacenNuevo.setCentro(cRepository.get(almacen.getCodCentro()));

		aRepository.add(almacenNuevo);

	}

	public Almacen getnomAlmacen(String nomAlmacen, Integer codCentro) {
		// TODO Auto-generated method stub
		List<Almacen> almacenes = aRepository.getNomAlmacen(nomAlmacen,
				codCentro);

		Almacen almacen = new Almacen();
		almacen = null;

		for (Almacen almacen1 : almacenes) {

			if (almacenes.size() == 1) {

				almacen = almacen1;
			}

		}

		return almacen;
	}
	
	public Almacen getAlmacen(String nomAlmacen) {
		// TODO Auto-generated method stub
		List<Almacen> almacenes = aRepository.getAlmacen(nomAlmacen);

		Almacen almacen = new Almacen();
		almacen = null;

		for (Almacen almacen1 : almacenes) {

			if (almacenes.size() == 1) {

				almacen = almacen1;
			}

		}

		return almacen;
	}

}
