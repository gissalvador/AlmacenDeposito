package com.configuracion.tecnica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.articulo.dto.ArticuloDTO;
import com.articulo.modules.*;


@ManagedBean (name = "confArticuloNew")
@ViewScoped
public class confArticuloNew implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@EJB
private ArticuloServicesNew articuloService;
private ArticuloDTO articuloDTO;

	
private Collection<ArticuloDTO> articuloCOmp;

public String guardarArticulo(){
	try {
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		articuloDTO.setUsuario(request.getUserPrincipal().getName());
		System.out.println(articuloDTO.getDescArticulo().length());
		articuloService.addArticulo(articuloDTO);
		
		articuloDTO = null;
		return "/general/articulo.xhtml";
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

public Collection<ArticuloDTO> getArticuloCOmp() {
	return articuloCOmp;
}
public void setArticuloCOmp(Collection<ArticuloDTO> articuloCOmp) {
	this.articuloCOmp = articuloCOmp;
}
public ArticuloDTO getArticuloDTO() {
	return articuloDTO;
}
public void setArticuloDTO(ArticuloDTO articuloDTO) {
	this.articuloDTO = articuloDTO;
}
@PostConstruct
private void initialize() {
	
	if ( articuloDTO == null) {
		articuloDTO = new ArticuloDTO();
		articuloCOmp = new ArrayList<ArticuloDTO>();
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
