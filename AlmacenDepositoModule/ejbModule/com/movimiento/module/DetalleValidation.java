package com.movimiento.module;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.application.exceptions.ValidationError;
import com.articulo.entities.Articulo;
import com.articulo.repository.ArticuloRepository;
import com.movimiento.dto.DetMovEntradaDTO;
import com.movimiento.dto.MovEntradaDTO;
import com.movimiento.entities.Modelo;
import com.movimiento.repository.ModeloRepository;

/**
 * Session Bean implementation class DetalleValidation
 */
@Stateless
@LocalBean
public class DetalleValidation {

    /**
     * Default constructor. 
     */
	
	@EJB
	ArticuloRepository artRepository;
	
	@EJB
	ModeloRepository modRepository;
	
     // TODO Auto-generated constructor stub
    	
    	public List<ValidationError> validarDetalle(DetMovEntradaDTO ddto) {
    	List<ValidationError> errors = new ArrayList<ValidationError>();
    	
			
			if (ddto.getCodArticulo() == null || ddto.getCodArticulo() <= 0 ) {
				errors.add(new ValidationError("Articulo", " Debe definir un número de Artículo."));
			}   else {
				
				Articulo articulo = artRepository.get(ddto.getCodArticulo());
				if (articulo == null){
					errors.add(new ValidationError("Articulo:", "El Articulo "+ ddto.getCodArticulo() +" no se encuentra registrado."));
				} else{
					
					Date fechaActual = new Date();
					
					System.out.println(articulo.getVto());
					
					if (articulo.getVto() == true){
						
						if(ddto.getFechaVto() == null ||  ddto.getFechaVto().before(fechaActual) ){
							
							errors.add(new ValidationError("Fecha vencimiento:", "El articulo: "+ddto.getCodArticulo() +" requiere fecha de vto. valida " + ddto.getFechaVto() +" verifique los datos"));
							
						}
					
				}
			}
			
			
			
				
				if (ddto.getModelo() == null || ddto.getModelo() <= 0 ) {
					errors.add(new ValidationError("Modelo", "Debe definir un numero de Modelo."));
				}   else {
					
					Modelo modelo = modRepository.get(ddto.getModelo());
					if (modelo == null){
						errors.add(new ValidationError("Modelo:", " El Modelo "+ ddto.getModelo() + " no se encuentra registrado."));
					} 
					
					
				}
			
			
				
			} 
			
			return errors;
			
			}
   
    

}
