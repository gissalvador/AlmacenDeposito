package com.configuracion.tecnica;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang.math.NumberUtils;

import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.movimiento.dto.MarcaDTO;
import com.movimiento.dto.ModeloDTO;
import com.movimiento.module.MarcaService;
import com.movimiento.module.ModeloService;





@ManagedBean (name = "confModelo")
@ViewScoped
public class confModelo implements Serializable {
	
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
	
	@EJB
	private ModeloService modeloService;
	@EJB
	private MarcaService marcaService;
	
	private ModeloDTO modeloDTO;
	
	private MarcaDTO marca;
	

	
	@PostConstruct
	private void initialize() {
		int id = NumberUtils.toInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"), 0);
		marca = marcaService.findById(id);
		
		if ( modeloDTO == null) {
			modeloDTO = new ModeloDTO();
			
		} 
		
		modeloDTO.setMarca(marca.getNomMarca());
		modeloDTO.setMarcaId(marca.getCodMarca());
		
	}
		

		
	
	public String guardarModelo() throws IOException {
		try {
			
				modeloService.addModelo(modeloDTO);
			
			return "/tecnica2/marcaEditar.xhtml";
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
		return "/tecnica2/marcaEditar.xhtml";
	}
	
	
	
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




	public ModeloService getModeloService() {
		return modeloService;
	}




	public void setModeloService(ModeloService modeloService) {
		this.modeloService = modeloService;
	}




	public MarcaService getMarcaService() {
		return marcaService;
	}




	public void setMarcaService(MarcaService marcaService) {
		this.marcaService = marcaService;
	}




	public ModeloDTO getModeloDTO() {
		return modeloDTO;
	}




	public void setModeloDTO(ModeloDTO modeloDTO) {
		this.modeloDTO = modeloDTO;
	}




	public MarcaDTO getMarca() {
		return marca;
	}




	public void setMarca(MarcaDTO marca) {
		this.marca = marca;
	}
	
	
	
	

}
