package com.general;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.movimiento.dto.DetMovEntradaDTO;
import com.movimiento.dto.MovEntradaDTO;
import com.movimiento.module.MovEntradaService;


@ManagedBean (name = "detMovEntrada")
@ViewScoped
public class DetMovEntrada implements Serializable {
	
/**
	 * 
	 */
private static final long serialVersionUID = 1L;

@EJB
private MovEntradaService movEntradaService;



private MovEntradaDTO movEntrada;
private boolean nuevo = true;

@PostConstruct
private void initialize() {
		
	
	String id = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"); 
	movEntrada = movEntradaService.findById(id);
	movEntrada = movEntradaService.cargarPP(movEntrada);
	
	
	if (movEntrada == null) {
		movEntrada = new MovEntradaDTO();
		
	} else {
		nuevo = false;
	}
}

public List<DetMovEntradaDTO> getListDetalleDTO() {
	return movEntrada.getListDetalleDTO();
} 

public MovEntradaService getMovEntradaService() {
	return movEntradaService;
}

public void setMovEntradaService(MovEntradaService movEntradaService) {
	this.movEntradaService = movEntradaService;
}

public MovEntradaDTO getMovEntrada() {
	return movEntrada;
}

public void setMovEntrada(MovEntradaDTO movEntrada) {
	this.movEntrada = movEntrada;
}



}
