package com.institucional.module;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.clases.propias.CapitalizeString;
import com.institucional.dto.ActividadDTO;
import com.institucional.dto.ActividadFactory;
import com.institucional.entities.Actividad;
import com.institucional.repository.ActividadRepository;
import com.institucional.repository.EmpleadoRepository;



/**
 * Session Bean implementation class ActividadService
 */
@Stateless
@LocalBean
public class ActividadService implements ActividadServiceRemote {

	
	@EJB
	private ActividadRepository aRepository;
	
	@EJB
	private EmpleadoRepository eRepository;
	
	@EJB
	private ActividadValidations validator;
	
	@EJB
	private EmpleadoService eService;

	
    /**
     * Default constructor. 
     */
    public ActividadService() {
        // TODO Auto-generated constructor stub
    }
    
   
    @Override
	public ActividadDTO findById(Integer actividadId) {
		return ActividadFactory.getActividadDTO(aRepository.get(actividadId));
	}
	@Override
	public Collection<ActividadDTO> listAll() {
		return ActividadFactory.getActividadDTO(aRepository.getAll());
	}
	
	public void addActividad(ActividadDTO actividad){
		List<ValidationError> errors = validator.validarAddActividad(actividad);
		if (errors.size()>0){
			throw new BusinessException("Errores al agregar el actividad",errors);
		}
		Actividad actividadNuevo = new Actividad();
		

		actividadNuevo.setNomActividad(CapitalizeString.capitalizeString(actividad.getNomActividad()));
		actividadNuevo.setNroActividad(actividad.getNroActividad());
		actividadNuevo.setRedProgramatica(actividad.getRedProgramatica());
		actividadNuevo.setResponsable(eService.getEmpleado(actividad.getNroRes()));
		actividadNuevo.setSubresponsable(eService.getEmpleado(actividad.getNroSubRes()));
		
		
		aRepository.add(actividadNuevo);
		
	}
	
	public Actividad getActividad(Integer nroActividad){
		   List <Actividad> actList = aRepository.getActividad(nroActividad);
		  
		   Actividad act= new Actividad();
		   act = null;
		   
		   for(Actividad act1: actList){
			   
			   if(actList.size() == 1){
				   
				   act = act1;
			   }
			   
		   }
		   
		   return act;
	   }
	
	public Actividad getNroActividad(Integer nroActividad) {
		// TODO Auto-generated method stub
		List<Actividad> actividades = aRepository.getNroActividad(nroActividad);

		Actividad actividad = new Actividad();
		actividad = null;

		for (Actividad actividad1 : actividades) {

			if (actividades.size() == 1) {

				actividad = actividad1;
			}

		}
		
		return actividad;
	}


}
