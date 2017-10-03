package com.institucional.module;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.institucional.dto.UnidadOrganicaDTO;
import com.institucional.dto.UnidadOrganicaFactory;
import com.institucional.entities.UnidadOrganica;
import com.institucional.repository.UnidadOrganicaRepository;

/**
 * Session Bean implementation class UnidadOrganicaService
 */
@Stateless
@LocalBean
public class UnidadOrganicaService implements UnidadOrganicaServiceRemote {
	
	@EJB
	private UnidadOrganicaRepository uoRepository;
	
	@EJB
	private EmpleadoService eService;

    /**
     * Default constructor. 
     */
    public UnidadOrganicaService() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
   	public UnidadOrganicaDTO findById(Integer unidadOrganicaId) {
   		return UnidadOrganicaFactory.getUnidadOrganicaDTO(uoRepository.get(unidadOrganicaId));
   	}
   	@Override
   	public Collection<UnidadOrganicaDTO> listAll() {
   		return UnidadOrganicaFactory.getUnidadOrganicaDTO(uoRepository.getAll());
   	}
   	
	@Override
   	public void addUnidadOrganica(UnidadOrganicaDTO unidadOrganica){
   	/*	List<ValidationError> errors = validator.validarAddUnidadOrganica(unidadOrganica);
   		if (errors.size()>0){
   			throw new BusinessException("Errores al agregar el unidadOrganica",errors);
   		}*/
   		UnidadOrganica unidadOrganicaNuevo = new UnidadOrganica();
   		
		
   		unidadOrganicaNuevo.setNroDependencia(unidadOrganica.getNroDependencia());
   		unidadOrganicaNuevo.setNomUnidaOrganica(unidadOrganica.getNomUnidaOrganica());
   		if(unidadOrganica.getLegajo() != null){
   			
   			unidadOrganicaNuevo.setEsJefe(eService.getEmpleado(unidadOrganica.getLegajo()));
   			
   		}
   		
   		if(unidadOrganica.getDepende() != null){
   			
   			unidadOrganicaNuevo.setDepende(uoRepository.get(unidadOrganica.getDepende()));
   			
   		}
   	
   		
   		uoRepository.add(unidadOrganicaNuevo);
   		
   	}
   	
 

}
