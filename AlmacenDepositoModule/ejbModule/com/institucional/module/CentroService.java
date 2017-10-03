package com.institucional.module;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.institucional.dto.CentroDTO;
import com.institucional.dto.CentroFactory;
import com.institucional.entities.Centro;
import com.institucional.repository.CentroRepository;

import com.clases.propias.CapitalizeString;



/**
 * Session Bean implementation class CentroService
 */

@Stateless
@LocalBean
public class CentroService implements CentroServiceRemote {


	@EJB
	private CentroRepository cRepository;
	
	@EJB
	private CentroValidations validator;
	
	
	
    /**
     * Default constructor. 
     */
    public CentroService() {
        // TODO Auto-generated constructor stub
    }
    
    
    
    @Override
	public CentroDTO findById(Integer centroId) {
		return CentroFactory.getCentroDTO(cRepository.get(centroId));
	}
	@Override
	public Collection<CentroDTO> listAll() {
		return CentroFactory.getCentroDTO(cRepository.getAll());
	}
	
	@Override
	public void addCentro(CentroDTO centro){
	List<ValidationError> errors = validator.validarAddCentro(centro);
		if (errors.size()>0){
			throw new BusinessException("Errores al agregar el centro",errors);
		}
		Centro centroNuevo = new Centro();
		

		centroNuevo.setNomCentro( CapitalizeString.capitalizeString(centro.getNomCentro()));
		System.out.println(centroNuevo);	
		cRepository.add(centroNuevo);
		
	}
	
	  public Centro getnomCentro(String nombreCentro){
		   List <Centro> centros = cRepository.getNomCentro(nombreCentro);
		  
		   Centro centro= new Centro();
		   centro = null;
		   
		   for(Centro centro1: centros){
			   
			   if(centros.size() == 1){
				   
				   centro = centro1;
			   }
			   
		   }		
		      
		   return centro;
	   }


}
