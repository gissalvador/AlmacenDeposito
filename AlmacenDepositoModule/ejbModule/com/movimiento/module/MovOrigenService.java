package com.movimiento.module;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.movimiento.dto.MovEntradaDTO;
import com.movimiento.dto.MovSalidaDTO;
import com.movimiento.entities.MovOrigen;
import com.movimiento.entities.TipoMovOrigen;
import com.movimiento.repository.MovOrigenRepository;
import com.movimiento.repository.TipoMORepository;

/**
 * Session Bean implementation class MovOrigenService
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class MovOrigenService implements MovOrigenServiceRemote {

	@EJB
	private TipoMORepository tmoRepository;
	@EJB
	private MovOrigenRepository movoRepository;

	/**
	 * Default constructor.
	 */
	public MovOrigenService() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * Crear Movimiento que origina el movimiento de entrada si el movimento ya
	 * ha sido creado devuelve el objeto existente en la Base de Datos
	 * 
	 */

	public MovOrigen addMovOrigen(MovEntradaDTO movEntrada) {
		// List<ValidationError> errors =
		// validator.validarAddProducto(producto);
		/*
		 * if (errors.size() > 0) { throw new
		 * BusinessException("Errores al agregar el producto.", errors); }
		 */

		String result;

		result = movoRepository.getNroComprobante(movEntrada.getNroComprobante());

		MovOrigen mo = new MovOrigen();

		if (result == null) {

			TipoMovOrigen tmo = tmoRepository.get(movEntrada.getComprobante());

			mo.setTipoMovOrigen(tmo);
			mo.setFechaMovOrigen(movEntrada.getFechaMO());
			mo.setIdPersonaMovOrigen(movEntrada.getCuitlegajo());
			mo.setNroComprobanteMO(movEntrada.getNroComprobante());

			movoRepository.add(mo);

		} else {

			mo = movoRepository.get(result);

		}

		return mo;
	}

	public MovOrigen addMovOrigen(MovSalidaDTO movSalida, Integer tMovSalida) {
		// TODO Auto-generated method stub

		MovOrigen mo = new MovOrigen();

		TipoMovOrigen tmo = tmoRepository.get(tMovSalida);

		mo.setTipoMovOrigen(tmo);
		mo.setFechaMovOrigen(movSalida.getFechaSalida());
		mo.setIdPersonaMovOrigen(movSalida.getLegajo());
		if(tMovSalida == 7){
			
			mo.setNroComprobanteMO(movSalida.getNroComprobante());
			
		}
		
		

		movoRepository.add(mo);

		return mo;
	}

}
