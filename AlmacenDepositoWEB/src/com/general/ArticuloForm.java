package com.general;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.articulo.dto.ArticuloDTO;
import com.articulo.modules.ArticuloServicesNewRemote;




@ManagedBean
@ViewScoped
public class ArticuloForm implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ArticuloServicesNewRemote aService;
	
	private List<ArticuloDTO>  arts;

	
	private List<ArticuloDTO>  filteredArts;

	
	public List<ArticuloDTO> getFilteredArts() {
		return filteredArts;
	}



	public void setFilteredArts(List<ArticuloDTO> filteredArts) {
		this.filteredArts = filteredArts;
	}
	
		
	public List<ArticuloDTO> getArts() {
		return arts;
	}


	public void setArts(List<ArticuloDTO> arts) {
		this.arts = arts;
	}


	@PostConstruct
    public void init() {
        arts = (List<ArticuloDTO>) aService.listAll();
    }
	
	

}
