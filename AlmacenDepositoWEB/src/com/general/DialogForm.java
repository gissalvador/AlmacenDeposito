package com.general;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;
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

	public void seleccionarFila() {

	
		System.out.println("puto8");

		System.out.println("puto");
		
		// articulo = art;

	}
	
	public void seleccionarFila2(ArticuloDTO art) throws IOException {

		
		System.out.println("puto8");

		System.out.println("puto");
		
		articulo = art;
		
		this.codArticulo = art.getCodArticulo();
		
		System.out.println(art.getCodArticulo());
		
		 ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		 ec.redirect(ec.getRequestContextPath() + "/general/dialog.xhtml");
		
		
		
		this.changeIn();

	}
	
	public void changeIn() {
	    System.out.println("in has been changed to " );
	}
	
	public void update()
    {
               
		System.out.println("puto2");

       
    }

	public void login() {
		//RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage message = null;
		System.out.println("puto");
		boolean loggedIn = false;

		

		//FacesContext.getCurrentInstance().addMessage(null, message);
		//context.addCallbackParam("loggedIn", loggedIn);
	}
	
	public void save(ActionEvent event) {
		//RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage message = null;
		System.out.println("puto8");
		boolean loggedIn = false;

		

		//FacesContext.getCurrentInstance().addMessage(null, message);
		//context.addCallbackParam("loggedIn", loggedIn);
	}

}
