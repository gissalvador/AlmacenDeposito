package com.movimiento.module;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.movimiento.dto.MovEntradaDTO;
import com.movimiento.entities.Solicitud;
import com.movimiento.repository.SolicitudRepository;


/**
 * Session Bean implementation class SolicitudService
 */
@Stateless
@LocalBean
public class SolicitudService implements SolicitudServiceRemote {
	
	@EJB
	private SolicitudRepository sRepository;

    /**
     * Default constructor. 
     */
    public SolicitudService() {
        // TODO Auto-generated constructor stub
    }

	
	/** 
	 * 
	 * Crear solicitud que origina el movimiento de entrada
	 * si la solicitud ya ha sido creado 
	 * devuelve el objeto existente en la Base de Datos 
	 * 
	 * */
    
	public Solicitud addSolicitud(MovEntradaDTO movEntrada) {
		//List<ValidationError> errors = validator.validarAddProducto(producto);
		/*if (errors.size() > 0) {
			throw new BusinessException("Errores al agregar el producto.", errors);
		}
		
		
		 String result;
		
		  result= sRepository.getNroSolicitud(movEntrada.getNroSolicitud()); */

		  Solicitud s = new Solicitud();
		  
		//if ( result == null){
			
			s.setNroPedidoCompra(movEntrada.getNroSolicitud());		
			   	
	    	sRepository.add(s);
			
	/*	} else{
			
			s = sRepository.get(result);
			
		}
		*/
				
		 
		 return s;
	}
}
