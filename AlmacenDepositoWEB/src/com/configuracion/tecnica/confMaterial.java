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
import com.articulo.dto.MaterialDTO;
import com.articulo.modules.*;


@ManagedBean (name = "confMaterial")
@ViewScoped
public class confMaterial implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@EJB
private MaterialService materialService;
private MaterialDTO materialDTO;



public String guardarMaterial(){
	try {
		materialService.addMaterial(materialDTO);
		return "/general/material.xhtml";
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


public MaterialDTO getMaterialDTO() {
	return materialDTO;
}
public void setMaterialDTO(MaterialDTO materialDTO) {
	this.materialDTO = materialDTO;
}
@PostConstruct
private void initialize() {
	
	if ( materialDTO == null) {
		materialDTO = new MaterialDTO();
		
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
