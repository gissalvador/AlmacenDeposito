package com.general;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.movimiento.dto.LoteAlmacenDTO;
import com.movimiento.dto.LoteDTO;
import com.movimiento.module.LoteService;


@ManagedBean (name = "detalleLote")
@ViewScoped
public class DetalleLote implements Serializable {
	

private static final long serialVersionUID = 1L;

@EJB
private LoteService loteService;


private LoteDTO lote;
private boolean nuevo = true;

@PostConstruct
private void initialize() {
		
	
	String id = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"); 
	lote = loteService.findById(id);
		
	
	if (lote == null) {
		lote = new LoteDTO();
		
	} else {
		nuevo = false;
	}
}

public List<LoteAlmacenDTO> getLoteAlmacen() {
	return lote.getLoteAlmacenDTO();
}

public LoteDTO getLote() {
	return lote;
}

public void setLote(LoteDTO lote) {
	this.lote = lote;
} 


}
