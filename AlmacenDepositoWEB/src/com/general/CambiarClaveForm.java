package com.general;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.movimiento.dto.MovEntradaDTO;
import com.seguridad.CambioClaveServiceRemote;

/**
 * Formulario que muestra la grilla de CambioClavees.
 * 
 * @author Gisella
 * 
 */
@ManagedBean
@SessionScoped
public class CambiarClaveForm {

	@EJB
	private CambioClaveServiceRemote cambioClaveService;
	private String login;
	private String codR;
	private String bl;
	private String bl2;
	private String plainTextPasswordNuevo;
	private String plainTextPasswordNuevo2;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getCodR() {
		return codR;
	}

	public void setCodR(String codR) {
		this.codR = codR;
	}

	public String getBl() {
		return bl;
	}

	public void setBl(String bl) {
		this.bl = bl;
	}

	public String getBl2() {
		return bl2;
	}

	public void setBl2(String bl2) {
		this.bl2 = bl2;
	}
	
	

	public String getPlainTextPasswordNuevo() {
		return plainTextPasswordNuevo;
	}

	public void setPlainTextPasswordNuevo(String plainTextPasswordNuevo) {
		this.plainTextPasswordNuevo = plainTextPasswordNuevo;
	}

	public String getPlainTextPasswordNuevo2() {
		return plainTextPasswordNuevo2;
	}

	public void setPlainTextPasswordNuevo2(String plainTextPasswordNuevo2) {
		this.plainTextPasswordNuevo2 = plainTextPasswordNuevo2;
	}

	public String enviarCambioClave() {

		try {

			cambioClaveService.recuperarClave(login);

			bl = "recuperer contraseña";

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

	public String comprobarCodigo() {

		try {

			//System.out.println("hola mundo");

			cambioClaveService.comprobarCodigo(login, codR);

			bl2 = "comprobar Codigo";

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

	public String cambiarContrasenia() {

		try {
			
			bl = null;
			bl2 = null;

			if (!validateSave()) {
				return null;
			}
			
			cambioClaveService.cambiarContrasenia(login, plainTextPasswordNuevo);
		

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

		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cambiarClaveForm", null);
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		return "/index.xhtml";

	}
	
	public boolean validateSave() {
		if (plainTextPasswordNuevo.trim().length() > 0 || plainTextPasswordNuevo2.trim().length() > 0) {
			if (!plainTextPasswordNuevo.equals(plainTextPasswordNuevo2)) {
				FacesContext.getCurrentInstance().addMessage("form:plainTextPasswordNuevo", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las contraseñas no coinciden.", "Las contraseñas no coinciden."));
			}
		}

		return FacesContext.getCurrentInstance().getMessageList().isEmpty();
	}

	@PostConstruct
	private void initialize() {

	}
	
	public String cancelar() {
		
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		return "/index.xhtml"; 
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
