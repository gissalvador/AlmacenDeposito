package com.configuracion.administrativa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.institucional.dto.EmpleadoDTO;
import com.institucional.dto.UnidadOrganicaDTO;
import com.institucional.module.EmpleadoService;
import com.institucional.module.UnidadOrganicaServiceRemote;


@ManagedBean (name = "confEmpleado")
@ViewScoped
public class confEmpleado implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@EJB
private EmpleadoService empleadoService;
@EJB
private UnidadOrganicaServiceRemote unidadOrganicaService;
private EmpleadoDTO empleadoDTO;
private String nombre;
private String apellido;
private String uOrganica;
Collection<UnidadOrganicaDTO> listUO = new ArrayList<UnidadOrganicaDTO>();
private List<String> options;
 




public String guardarEmpleado(){
	try {
		for (UnidadOrganicaDTO uo : listUO) {

			if(uo.getNroDependencia().equals(uOrganica)){
				
				empleadoDTO.setNroUOrganica(uo.getCodUnidadOrganica());
				
			}
		}
		empleadoDTO.setNomPersona(apellido + ", "+ nombre);
		empleadoService.addEmpleado(empleadoDTO);
		return "/general/empleado.xhtml";
	} catch (BusinessException be) {
		processBusinessException(be);
	} catch (ConstraintViolationException cve) {
		processConstraintViolationException(cve);
	} catch (EJBException e) {
		if (e.getCause().getClass().isAssignableFrom(ConstraintViolationException.class)) {
			processConstraintViolationException((ConstraintViolationException) e.getCause());
		} else if (e.getCause().getClass().isAssignableFrom(BusinessException.class)) {
			processBusinessException((BusinessException) e.getCause());
		}
	} catch (Exception e) {
		e.printStackTrace();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error inesperado del sistema. No se pudo agregar el Producto.", e.getMessage()));
	}
	return null;	
	
	
	
}


public EmpleadoDTO getEmpleadoDTO() {
	return empleadoDTO;
}
public void setEmpleadoDTO(EmpleadoDTO empleadoDTO) {
	this.empleadoDTO = empleadoDTO;
}

public String getNombre() {
	return nombre;
}


public void setNombre(String nombre) {
	this.nombre = nombre;
}


public String getApellido() {
	return apellido;
}


public void setApellido(String apellido) {
	this.apellido = apellido;
}



public String getuOrganica() {
	return uOrganica;
}


public void setuOrganica(String uOrganica) {
	this.uOrganica = uOrganica;
}


public Collection<UnidadOrganicaDTO> getListUO() {
	return listUO;
}


public void setListUO(Collection<UnidadOrganicaDTO> listUO) {
	this.listUO = listUO;
}


public List<String> getOptions() {
	return options;
}


public void setOptions(List<String> options) {
	this.options = options;
}

public Collection<UnidadOrganicaDTO> getUnidadOrganicas() {
	return unidadOrganicaService.listAll();
}

@PostConstruct
private void initialize() {
	
	listUO = this.getUnidadOrganicas();

	options = new ArrayList<String>();

	for (UnidadOrganicaDTO uo : listUO) {

		options.add(uo.getNroDependencia());

	}
	
	if ( empleadoDTO == null) {
		empleadoDTO = new EmpleadoDTO();
		
	} }
/**
 * Procesa y revisa las excepciones de negocio obtenidas.
 * 
 * @param e
 */
private void processBusinessException(BusinessException e) {
	BusinessException be = (BusinessException) e;
	if (be.getErrores().size() > 0) {
		for (ValidationError ve : be.getErrores()) {
			FacesContext.getCurrentInstance().addMessage("form:" + ve.getProperty(), new FacesMessage(FacesMessage.SEVERITY_ERROR, ve.getError(), ve.getError()));
		}
	} else {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, be.getMessage(), be.getMessage()));
	}}

/**
 * Procesa y revisa las excepciones de negocio obtenidas.
 * 
 * @param cve
 */
private void processConstraintViolationException(ConstraintViolationException cve) {
	for (ConstraintViolation<?> v : cve.getConstraintViolations()) {
		FacesContext.getCurrentInstance().addMessage("form:" + v.getPropertyPath().toString(), new FacesMessage(FacesMessage.SEVERITY_ERROR, v.getMessage(), v.getMessage()));
	}
}
}
