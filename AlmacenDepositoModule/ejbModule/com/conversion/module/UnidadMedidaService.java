package com.conversion.module;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;


import com.conversion.dto.UnidadMedidaDTO;
import com.conversion.dto.UnidadMedidaFactory;
import com.conversion.entities.UnidadMedida;
import com.conversion.repository.TipoUMRepository;
import com.conversion.repository.UnidadMedidaRepository;


/**
 * Session Bean implementation class UnidadMedidaService
 */
@Stateless
@LocalBean
public class UnidadMedidaService implements UnidadMedidaServiceRemote {

	
	@EJB
	private UnidadMedidaRepository umRepository;
	
	@EJB
	private TipoUMRepository tuRepository;
	
		
    /**
     * Default constructor. 
     */
	
	
    public UnidadMedidaService() {
        // TODO Auto-generated constructor stub
    }
    
       
    @Override
	public UnidadMedidaDTO findById(Integer unidadMedidaId) {
		return UnidadMedidaFactory.getUnidadMedidaDTO(umRepository.get(unidadMedidaId));
	}
	@Override
	public Collection<UnidadMedidaDTO> listAll() {
		return UnidadMedidaFactory.getUnidadMedidaDTO(umRepository.getAll());
	}
	
	public void addUnidadMedida(UnidadMedidaDTO unidadMedida){
	/*	List<ValidationError> errors = validator.validarAddUnidadMedida(unidadMedida);
		if (errors.size()>0){
			throw new BusinessException("Errores al agregar el unidadMedida",errors);
		}*/
		UnidadMedida unidadMedidaNuevo = new UnidadMedida();
		

		unidadMedidaNuevo.setNombre(unidadMedida.getNombre());
		unidadMedidaNuevo.setSimbolo(unidadMedida.getSimbolo());
		unidadMedidaNuevo.setTipoUM(tuRepository.get(unidadMedida.getCodTUM()));
		
		umRepository.add(unidadMedidaNuevo);
		
	}

}
