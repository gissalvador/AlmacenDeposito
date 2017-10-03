package com.movimiento.module;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
 






import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.clases.propias.CapitalizeString;
import com.institucional.repository.TipoPersonaRepository;
import com.movimiento.dto.TipoMovOrigenDTO;
import com.movimiento.dto.TipoMovOrigenFactory;
import com.movimiento.entities.TipoMovOrigen;
import com.movimiento.repository.TipoMORepository;

/**
 * Session Bean implementation class TipoMovOrigenService
 */
@Stateless
@LocalBean
public class TipoMovOrigenService implements TipoMovOrigenServiceRemote {

	

	@EJB
	private TipoMORepository tmoRepository;
	
	@EJB
	private TipoPersonaRepository tpRepository;
	
	@EJB
	private TipoMovOrigenValidation validator;
	
    /**
     * Default constructor. 
     */
    public TipoMovOrigenService() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
   	public TipoMovOrigenDTO findById(Integer tipoMovOrigenId) {
   		return TipoMovOrigenFactory.getTipoMovOrigenDTO(tmoRepository.get(tipoMovOrigenId));
   	}
   	@Override
   	public Collection<TipoMovOrigenDTO> listAll() {
   		return TipoMovOrigenFactory.getTipoMovOrigenDTO(tmoRepository.getAll());
   	}
   	
   	@Override
   	public void addTipoMovOrigen(TipoMovOrigenDTO tipoMovOrigen){
   	List<ValidationError> errors = validator.validarAddTipoMovOrigen(tipoMovOrigen);
   		if (errors.size()>0){
   			throw new BusinessException("Errores al agregar el tipoMovOrigenService",errors);
   		}
   		TipoMovOrigen tipoMovOrigenNuevo = new TipoMovOrigen();
   		

   		tipoMovOrigenNuevo.setNomTMovOrigen(CapitalizeString.capitalizeString(tipoMovOrigen.getNomTMovOrigen()));
   		tipoMovOrigenNuevo.setTipoPersona(tpRepository.get(tipoMovOrigen.getNroTipoPersona()));
   		
   		tmoRepository.add(tipoMovOrigenNuevo);
   		
   	}

	public TipoMovOrigen getnomTMovOrigen(String nomTMovOrigen) {
		// TODO Auto-generated method stub
		  List <TipoMovOrigen> tipoMOs = tmoRepository.getNomTMovOrigen(nomTMovOrigen);
		  
		   TipoMovOrigen tipoMO= new TipoMovOrigen();
		   tipoMO = null;
		   
		   for(TipoMovOrigen tipoMO1: tipoMOs){
			   
			   if(tipoMOs.size() == 1){
				   
				   tipoMO = tipoMO1;
			   }
			   
		   }		
		
		return tipoMO;
	}

}
