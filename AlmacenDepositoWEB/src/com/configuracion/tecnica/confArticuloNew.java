package com.configuracion.tecnica;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
import com.articulo.dto.MaterialDTO;
import com.articulo.dto.TipoArticuloDTO;
import com.articulo.modules.*;



@ManagedBean (name = "confArticuloNew")
@SessionScoped
public class confArticuloNew implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@EJB
private ArticuloServicesNew articuloService;

@EJB
private TipoArticuloServiceRemote tipoArticuloService;
private ArticuloDTO articuloDTO;

	
private Collection<ArticuloDTO> articuloCOmp;

private String tipoArticulo;
private List<String> optionsTArt;
Collection<TipoArticuloDTO> listTArt = new ArrayList<TipoArticuloDTO>();
TipoArticuloDTO tipoArticuloDTO;



public String getTipoArticulo() {
	return tipoArticulo;
}

public void setTipoArticulo(String tipoArticulo) {
	this.tipoArticulo = tipoArticulo;
}

public List<String> getOptionsTArt() {
	return optionsTArt;
}

public void setOptionsTArt(List<String> optionsTArt) {
	this.optionsTArt = optionsTArt;
}




public Collection<TipoArticuloDTO> getListTArt() {
	return listTArt;
}

public void setListTArt(Collection<TipoArticuloDTO> listTArt) {
	this.listTArt = listTArt;
}

public TipoArticuloDTO getTipoArticuloDTO() {
	return tipoArticuloDTO;
}

public void setTipoArticuloDTO(TipoArticuloDTO tipoArticuloDTO) {
	this.tipoArticuloDTO = tipoArticuloDTO;
}

public Collection<TipoArticuloDTO> getTArt() {
	return tipoArticuloService.listAll();
}

public String guardarArticulo(){
	try {
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		articuloDTO.setUsuario(request.getUserPrincipal().getName());
		articuloDTO.setIsComp(false);
		
		
		
		//System.out.println(articuloDTO.getDescArticulo().length());
		
		for (TipoArticuloDTO ta : listTArt) {

			if (ta.getDescArticulo().equals(tipoArticulo)) {

				articuloDTO.settArticulo(ta.getDescArticulo());
				articuloDTO.setTipoArticuloId(ta.getCodTipoArticulo());
			
			
			}
		}
		
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

public void seleccionarFilaM(MaterialDTO mat) throws IOException {
	
	System.out.println(mat.getCodMaterial());

	articuloDTO.setMaterialId(mat.getCodMaterial());

	
	System.out.println(articuloDTO.getMaterial());
	
	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	ec.redirect(ec.getRequestContextPath() + "/tecnica/conf-articulo-new.xhtml");

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
	}
	
	listTArt = this.getTArt();

	optionsTArt = new ArrayList<String>();
	
	for (TipoArticuloDTO ta : listTArt) {
		
		optionsTArt.add(ta.getNomArticulo());
		
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
