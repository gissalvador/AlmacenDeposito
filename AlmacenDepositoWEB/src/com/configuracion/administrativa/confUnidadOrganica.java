package com.configuracion.administrativa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.institucional.dto.UnidadOrganicaDTO;
import com.institucional.module.UnidadOrganicaServiceRemote;

@ManagedBean(name = "confUnidadOrganica")
@ViewScoped
public class confUnidadOrganica implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	@EJB
	private UnidadOrganicaServiceRemote unidadOrganicaService;
	private UnidadOrganicaDTO unidadOrganicaDTO;
	private List<String> options;
	
	Collection<UnidadOrganicaDTO> listUO = new ArrayList<UnidadOrganicaDTO>();

	public String guardarUnidadOrganica() {
		try {
			
			
			for (UnidadOrganicaDTO uo : listUO) {

				if(uo.getNroDependencia().equals(unidadOrganicaDTO.getNroDepende())){
					
					unidadOrganicaDTO.setDepende(uo.getCodUnidadOrganica());
					
				}
				

			}
			unidadOrganicaService.addUnidadOrganica(unidadOrganicaDTO);
			return "/general/unidadOrganica.xhtml";
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
					"Error inesperado del sistema. No se pudo agregar el Producto.", e.getMessage()));
		}
		return null;

	}

	public UnidadOrganicaDTO getUnidadOrganicaDTO() {
		return unidadOrganicaDTO;
	}

	public void setUnidadOrganicaDTO(UnidadOrganicaDTO unidadOrganicaDTO) {
		this.unidadOrganicaDTO = unidadOrganicaDTO;
	}

	public Collection<UnidadOrganicaDTO> getUnidadOrganicas() {
		return unidadOrganicaService.listAll();
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	@PostConstruct
	private void initialize() {

		

		listUO = this.getUnidadOrganicas();

		options = new ArrayList<String>();

		for (UnidadOrganicaDTO uo : listUO) {

			options.add(uo.getNroDependencia());

		}

		if (unidadOrganicaDTO == null) {
			unidadOrganicaDTO = new UnidadOrganicaDTO();

		}
	}

	/**
	 * Procesa y revisa las excepciones de negocio obtenidas.
	 * 
	 * @param e
	 */
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
