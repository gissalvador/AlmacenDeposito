package com.movimiento.module;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.articulo.repository.ArticuloRepository;
import com.movimiento.dto.DetMovSalidaDTO;
import com.movimiento.entities.Lote;
import com.movimiento.repository.LoteRepository;
import com.movimiento.repository.MovSalidaRepository;

/**
 * Session Bean implementation class DetalleValidation
 */
@Stateless
@LocalBean
public class DetalleTrasladoSalidaValidation {

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
    	
    	Lote lote = new Lote();
    	
    	lote = lRepository.get(ddto.getLote());
    	
    	if (ddto.getLote() == null) {
			errors.add(new ValidationError("Lote", "Debe definir un código de Lote."));
		}   else {
			
			
			if (lote == null){
				errors.add(new ValidationError("Lote:", "El Lote "+ ddto.getLote() + " no se encuentra registrado."));
			} 
		}
		
		if (ddto.getCantidad() == null || ddto.getCantidad() <= 0 ){
			
			errors.add(new ValidationError("Cantidad:", "Debe definir la cantidad del articulo " + ddto.getCodArticulo() +" correctamente"));
			
		}
		
		if (lote.getEstadoActualLote().getCodEstadoLote() == 3 ||  lote.getEstadoActualLote().getCodEstadoLote() == 5 || lote.getEstadoActualLote().getCodEstadoLote() == 7) {
			throw new BusinessException("Errores: No se puede realizar un movimiento de trslado con el  lote en el estado: " + lote.getEstadoActualLote().getNomEstadoLote());
		}
		
		/* else {
			
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
