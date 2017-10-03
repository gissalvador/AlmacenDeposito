package com.institucional.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.institucional.entities.Empleado;


public class EmpleadoFactory {
	
	public static EmpleadoDTO getEmpleadoDTO(Empleado empleado) {
		if(empleado == null) {
			return null;
		}

		EmpleadoDTO result = new EmpleadoDTO();
		result.setCodPersona(empleado.getCodPersona());
		result.setLegajo(empleado.getLegajo());
		result.setNomPersona(empleado.getNomPersona());
		result.setConResPatriminial(empleado.getConResPatriminial());
		result.setCuil(empleado.getCuil());
		result.setCelular(empleado.getCelular());
		result.setDomicilio(empleado.getDomicilio());
		result.setTelefono(empleado.getTelefono());
		result.setMail(empleado.getMail());
		result.setuOrganica(empleado.getUnidadOrganica().getNomUnidaOrganica());
		result.setNroUOrganica(empleado.getUnidadOrganica().getCodUnidadOrganica());
		
		
		
				
		return result;
	}
	
	public static Collection<EmpleadoDTO> getEmpleadoDTO(Collection<Empleado> empleados) {
		if(empleados == null) {
			return null;
		}

		
		List<EmpleadoDTO> retorno = new ArrayList<EmpleadoDTO>();
		for(Empleado empleado: empleados) {
			retorno.add(getEmpleadoDTO(empleado));
		}
		return retorno;
	}
	
}
