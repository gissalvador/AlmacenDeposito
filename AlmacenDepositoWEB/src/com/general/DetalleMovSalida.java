package com.general;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.movimiento.dto.DetMovSalidaDTO;
import com.movimiento.dto.MovSalidaDTO;
import com.movimiento.module.MovSalidaService;



@ManagedBean (name = "detMovSalida")
@ViewScoped
public class DetalleMovSalida implements Serializable {
	
/**
	 * 
	 */
private static final long serialVersionUID = 1L;


@EJB
private MovSalidaService movSalidaService;


private MovSalidaDTO movSalida;
private boolean nuevo = true;

@PostConstruct
private void initialize() {
		
	
	String id = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"); 
	movSalida = movSalidaService.findById(id);
	movSalida = movSalidaService.cargarPP(movSalida);
	
	
	if (movSalida == null) {
		movSalida = new MovSalidaDTO();
		
	} else {
		nuevo = false;
	}
}

public List<DetMovSalidaDTO> getListDetalleDTO() {
	return movSalida.getDetalleMS();
} 

public MovSalidaService getMovSalidaService() {
	return movSalidaService;
}

public void setMovSalidaService(MovSalidaService movSalidaService) {
	this.movSalidaService = movSalidaService;
}

public MovSalidaDTO getMovSalida() {
	return movSalida;
}

public void setMovSalida(MovSalidaDTO movSalida) {
	this.movSalida = movSalida;
}




}
