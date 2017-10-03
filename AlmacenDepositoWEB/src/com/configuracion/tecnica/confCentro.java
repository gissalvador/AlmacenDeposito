package com.configuracion.tecnica;

import java.io.Serializable;

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
import com.institucional.dto.CentroDTO;
import com.institucional.module.CentroService;


@ManagedBean (name = "confCentro")
@ViewScoped
public class confCentro implements Serializable {
	
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
@EJB
private CentroService centroService;
private CentroDTO centroDTO;



public String guardarCentro(){
	
	Integer err=0;
	try {
		centroService.addCentro(centroDTO);
		
		return "/general/centro.xhtml";
	} catch (BusinessException be) {
		err=1;
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
	
		if( err == 0){
		
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("confCentro", null);	
		 
		
		
		}
	return null;	
	
	
	
}


public CentroDTO getCentroDTO() {
	return centroDTO;
}
public void setCentroDTO(CentroDTO centroDTO) {
	this.centroDTO = centroDTO;
}
@PostConstruct
private void initialize() {
	
	if ( centroDTO == null) {
		centroDTO = new CentroDTO();
		
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
