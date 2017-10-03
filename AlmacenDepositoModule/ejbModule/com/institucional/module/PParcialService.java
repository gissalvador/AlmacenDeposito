package com.institucional.module;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.institucional.dto.PParcialDTO;
import com.institucional.dto.PParcialFactory;
import com.institucional.entities.PParcial;
import com.institucional.repository.IncisoRepository;
import com.institucional.repository.PParcialRepository;
import com.institucional.repository.PPrincipalRepository;

/**
 * Session Bean implementation class PParcialService
 */
@Stateless
@LocalBean
public class PParcialService implements PParcialServiceRemote {

	
	@EJB
	private PParcialRepository ppRepository;
	
	@EJB
	private PPrincipalRepository pprRepository;
	
	@EJB
	private IncisoRepository iRepository;

	@EJB
	private	PParcialRepository pparR;
    /**
     * Default constructor. 
     */
    public PParcialService() {
        // TODO Auto-generated constructor stub
    }
    
    
    @Override
	public PParcialDTO findById(Integer pparcialId) {
		return PParcialFactory.getPParcialDTO(ppRepository.get(pparcialId));
	}
    
    
	@Override
	public Collection<PParcialDTO> listAll() {
		return PParcialFactory.getPParcialDTO(ppRepository.getAll());
	}
	
	
	   public PParcial getPPar(Integer nroPPar){
		   List <PParcial> ppaList = pparR.getPPa(nroPPar);
		  
		   PParcial ppa= new PParcial();
		   ppa = null;
		   
		   for(PParcial ppa1: ppaList){
			   
			   if(ppaList.size() == 1){
				   
				   ppa = ppa1;
			   }
			   
		   }
		   
		   return ppa;
	   }

}
