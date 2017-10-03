package com.institucional.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.institucional.entities.Actividad;

public class ActividadFactory {
		
	
	public static ActividadDTO getActividadDTO(Actividad actividad) {
		if(actividad == null) {
			return null;
		}

		ActividadDTO result = new ActividadDTO();
		result.setCodActividad(actividad.getCodActividad());
		result.setNomActividad(actividad.getNomActividad());
		result.setNroActividad(actividad.getNroActividad());
		result.setRedProgramatica(actividad.getRedProgramatica());
		result.setResponsable(actividad.getResponsable().getNomPersona());
		result.setSubresponsable(actividad.getSubresponsable().getNomPersona());
		
		
		
		return result;
	}
	
	public static Collection<ActividadDTO> getActividadDTO(Collection<Actividad> actividades) {
		if(actividades == null) {
			return null;
		}

		
		List<ActividadDTO> retorno = new ArrayList<ActividadDTO>();
		for(Actividad actividad: actividades) {
			retorno.add(getActividadDTO(actividad));
		}
		return retorno;
	}

}
