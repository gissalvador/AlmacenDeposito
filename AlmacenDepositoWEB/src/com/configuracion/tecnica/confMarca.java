package com.configuracion.tecnica;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

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
import com.movimiento.entities.Modelo;
import com.movimiento.module.MarcaService;
import com.movimiento.module.ModeloService;




@ManagedBean (name = "confMarca")
@ViewScoped
public class confMarca  implements Serializable {
	
/**
	 * 
	 */
private static final long serialVersionUID = 1L;

@EJB
private MarcaService marcaService;


@EJB
private ModeloService modeloService;

private MarcaDTO marca;
private Collection<ModeloDTO> modelos;
private Modelo modeloEditada;
private boolean nuevo = true;


@PostConstruct
private void initialize() {
	int id = NumberUtils.toInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"), 0);
	marca = marcaService.findById(id);
	modelos = modeloService.listAllModelos(id);

	if (marca == null) {
		marca = new MarcaDTO();
		modelos = new ArrayList<ModeloDTO>();
	} else {
		nuevo = false;
	}
}

	public String save() throws IOException {
		try {
			if (nuevo) {
				marcaService.addMarca(marca);
			} else {
				
			}
			return "/general/marca.xhtml";
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

	public MarcaService getMarcaService() {
		return marcaService;
	}

	public void setMarcaService(MarcaService marcaService) {
		this.marcaService = marcaService;
	}

	public ModeloService getModeloService() {
		return modeloService;
	}

	public void setModeloService(ModeloService modeloService) {
		this.modeloService = modeloService;
	}

	public MarcaDTO getMarca() {
		return marca;
	}

	public void setMarca(MarcaDTO marca) {
		this.marca = marca;
	}

	public Collection<ModeloDTO> getModelos() {
		return modelos;
	}

	public void setModelos(Collection<ModeloDTO> modelos) {
		this.modelos = modelos;
	}

	public Modelo getModeloEditada() {
		return modeloEditada;
	}

	public void setModeloEditada(Modelo modeloEditada) {
		this.modeloEditada = modeloEditada;
	}

	public boolean isNuevo() {
		return nuevo;
	}

	public void setNuevo(boolean nuevo) {
		this.nuevo = nuevo;
	}

	


}
