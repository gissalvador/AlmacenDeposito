package com.institucional.module;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.institucional.dto.EmpleadoDTO;
import com.institucional.dto.EmpleadoFactory;
import com.institucional.entities.Empleado;
import com.institucional.repository.EmpleadoRepository;
import com.institucional.repository.TipoPersonaRepository;
import com.institucional.repository.UnidadOrganicaRepository;

/**
 * Session Bean implementation class EmpleadoService
 */
@Stateless
@LocalBean
public class EmpleadoService implements EmpleadoServiceRemote {

	
	@EJB
	private EmpleadoRepository eRepository;
	
	@EJB
	private TipoPersonaRepository tRepository;
	
	@EJB
	private UnidadOrganicaRepository upRepository;
    /**
     * Default constructor. 
     */
    public EmpleadoService() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
  	public EmpleadoDTO findById(Integer empleadoId) {
  		return EmpleadoFactory.getEmpleadoDTO(eRepository.get(empleadoId));
  	}
  	@Override
  	public Collection<EmpleadoDTO> listAll() {
  		return EmpleadoFactory.getEmpleadoDTO(eRepository.getAll());
  	}
  	
  	public void addEmpleado(EmpleadoDTO empleado){
  	/*	List<ValidationError> errors = validator.validarAddEmpleado(empleado);
  		if (errors.size()>0){
  			throw new BusinessException("Errores al agregar el empleado",errors);
  		}*/
  		Empleado empleadoNuevo = new Empleado();
  		

  		empleadoNuevo.setLegajo(empleado.getLegajo());
  		empleadoNuevo.setNomPersona(empleado.getNomPersona());
  		empleadoNuevo.setConResPatriminial(empleado.getConResPatriminial());
  		empleadoNuevo.setCuil(empleado.getCuil());
  		empleadoNuevo.setCelular(empleado.getCelular());
  		empleadoNuevo.setDomicilio(empleado.getDomicilio());
  		empleadoNuevo.setTelefono(empleado.getTelefono());
  		empleadoNuevo.setMail(empleado.getMail());
  		empleadoNuevo.setUnidadOrganica(upRepository.get(empleado.getNroUOrganica()));
  		empleadoNuevo.setTipoPersona(tRepository.get(empleado.getTipoPersona()));
  		
  		eRepository.add(empleadoNuevo);
  		
  	}
  	
  	public Empleado getEmpleado(String nroEmpleado){
		   List <Empleado> empList = eRepository.getEmpleado(nroEmpleado);
		  
		   Empleado emp= new Empleado();
		   emp = null;
		   
		   for(Empleado emp1: empList){
			   
			   if(empList.size() == 1){
				   
				   emp = emp1;
			   }
			   
		   }
		   
		   return emp;
	   }

      
    

}
