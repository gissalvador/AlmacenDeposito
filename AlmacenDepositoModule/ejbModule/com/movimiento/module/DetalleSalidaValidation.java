package com.movimiento.module;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.application.exceptions.ValidationError;
import com.articulo.entities.Articulo;
import com.articulo.repository.ArticuloRepository;
import com.movimiento.dto.DetMovSalidaDTO;
import com.movimiento.repository.LoteRepository;
import com.movimiento.repository.MovSalidaRepository;

/**
 * Session Bean implementation class DetalleValidation
 */
@Stateless
@LocalBean
public class DetalleSalidaValidation {

    /**
     * Default constructor. 
     */
	
	@EJB
	ArticuloRepository artRepository;
	
	
	@EJB
	LoteRepository lRepository;
	
	@EJB
	MovSalidaRepository	msRepository;
	
     // TODO Auto-generated constructor stub
    	
    	public List<ValidationError> validarDetalle(DetMovSalidaDTO ddto) {
    	List<ValidationError> errors = new ArrayList<ValidationError>();
    	
    	if (ddto.getCodArticulo() == null || ddto.getCodArticulo() <= 0 ) {
			errors.add(new ValidationError("Articulo", "Debe definir un numero de Articulo."));
		}   else {
			
			Articulo articulo = artRepository.get(ddto.getCodArticulo());
			if (articulo == null){
				errors.add(new ValidationError("Articulo:", "El Articulo "+ ddto.getCodArticulo() + " no se encuentra registrado."));
			} 
		}
		
		if (ddto.getCantidad() == null || ddto.getCantidad() <= 0 ){
			
			errors.add(new ValidationError("Cantidad:", "Debe definir la cantidad del articulo " + ddto.getCodArticulo() +" correctamente"));
			
		}/* else {
			
			Double stocktotal = lRepository.cantidadArticulo(movSalida.getAlmacen() , ddto.getCodArticulo());
			if (stocktotal <= ddto.getCantidad() ){
				
				errors.add(new ValidationError("Cantidad:", "La cantidad solicitada del articulo " + ddto.getCodArticulo() + " excede el stock total: "+ stocktotal));
				
			}
			System.out.println(stocktotal);
		}
		
		}
		}*/
		
		
		return errors;
			
			}
   
    

}
