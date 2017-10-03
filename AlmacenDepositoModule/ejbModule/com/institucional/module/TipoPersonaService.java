package com.institucional.module;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.institucional.dto.TipoPersonaDTO;
import com.institucional.dto.TipoPersonaFactory;
import com.institucional.entities.TipoPersona;
import com.institucional.repository.TipoPersonaRepository;

/**
 * Session Bean implementation class TipoPersonaService
 */
@Stateless
@LocalBean
public class TipoPersonaService implements TipoPersonaServiceRemote {
	
	@EJB
	private TipoPersonaRepository tpRepository;

    /**
     * Default constructor. 
     */
    public TipoPersonaService() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
   	public TipoPersonaDTO findById(Integer tipoPersonaId) {
   		return TipoPersonaFactory.getTipoPersonaDTO(tpRepository.get(tipoPersonaId));
   	}
   	@Override
   	public Collection<TipoPersonaDTO> listAll() {
   		return TipoPersonaFactory.getTipoPersonaDTO(tpRepository.getAll());
   	}
   	
   	public void addTipoPersona(TipoPersonaDTO tipoPersona){
   	/*	List<ValidationError> errors = validator.validarAddTipoPersona(tipoPersona);
   		if (errors.size()>0){
   			throw new BusinessException("Errores al agregar el tipoPersona",errors);
   		}*/
   		TipoPersona tipoPersonaNuevo = new TipoPersona();
   		

   		tipoPersonaNuevo.setNomTipoPersona(tipoPersona.getNomTipoPersona());
   		tipoPersonaNuevo.setNomTipoPersona(tipoPersona.getNomTipoPersona());
   		
   		
   		tpRepository.add(tipoPersonaNuevo);
   		
   	}


}
