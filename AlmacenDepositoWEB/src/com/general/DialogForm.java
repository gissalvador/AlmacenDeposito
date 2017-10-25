package com.general;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import java.io.IOException;


import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


import com.articulo.dto.ArticuloDTO;

@ManagedBean
@SessionScoped
public class DialogForm {

	private ArticuloDTO articulo;
	
	private Integer codArticulo;

	

	public Integer getCodArticulo() {
		return codArticulo;
	}

	public void setCodArticulo(Integer codArticulo) {
		this.codArticulo = codArticulo;
	}

	public ArticuloDTO getArticulo() {
		return articulo;
	}

	public void setArticulo(ArticuloDTO articulo) {
		this.articulo = articulo;
	}

	
	public void seleccionarFila2(ArticuloDTO art) throws IOException {

		
		articulo = art;
		
		this.codArticulo = art.getCodArticulo();
		
		System.out.println(art.getCodArticulo());
		
		 ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		 ec.redirect(ec.getRequestContextPath() + "/general/dialog.xhtml");
		

	}
	
}
