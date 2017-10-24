package com.general;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.email.module.EmailServiceRemote;



/**
 * Formulario que muestra la grilla de Emailes.
 * 
 * @author Gisella
 * 
 */
@ManagedBean
@RequestScoped
public class EmailForm {
	
	@EJB
	private EmailServiceRemote emailService;

	public String enviarEmail() {
		
		

		try {

			emailService.send("gisellasalvador@gmail.com", "Hola Mail", "wildfly te odio");
			

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

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error inesperado del sistema. No se pudo agregar el Movimiento de Entrada.", e.getMessage()));

			System.out.println("List<ValidationError> :" + e);
		}
		
		return null;
				
	}
	
	private void processBusinessException(BusinessException e) {
		BusinessException be = (BusinessException) e;
		if (be.getErrores().size() > 0) {
			for (ValidationError ve : be.getErrores()) {
				FacesContext.getCurrentInstance().addMessage("form:" + ve.getProperty(),
						new FacesMessage(FacesMessage.SEVERITY_ERROR, ve.getError(), ve.getError()));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, be.getMessage(), be.getMessage()));
		}
	}

	/**
	 * Procesa y revisa las excepciones de negocio obtenidas.
	 * 
	 * @param cve
	 */
	private void processConstraintViolationException(ConstraintViolationException cve) {
		for (ConstraintViolation<?> v : cve.getConstraintViolations()) {
			FacesContext.getCurrentInstance().addMessage("form:" + v.getPropertyPath().toString(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, v.getMessage(), v.getMessage()));
		}
	}

}
