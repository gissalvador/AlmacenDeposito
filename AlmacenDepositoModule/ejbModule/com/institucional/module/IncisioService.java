package com.institucional.module;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.institucional.dto.IncisoDTO;
import com.institucional.dto.IncisoFactory;
import com.institucional.repository.IncisoRepository;

/**
 * Session Bean implementation class IncisioService
 */
@Stateless
@LocalBean
public class IncisioService implements IncisioServiceRemote {

	@EJB
	private IncisoRepository iRepository;
	
	/**
     * Default constructor. 
     * 
     * 
     */
    public IncisioService() {
        // TODO Auto-generated constructor stub
    	
    	
    	
    }

    @Override
	public IncisoDTO findById(Integer incisoId) {
		return IncisoFactory.getIncisoDTO(iRepository.get(incisoId));
	}
	@Override
	public Collection<IncisoDTO> listAll() {
		return IncisoFactory.getIncisoDTO(iRepository.getAll());
	}
	

	
}
