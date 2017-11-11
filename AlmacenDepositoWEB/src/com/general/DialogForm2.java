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
import com.articulo.dto.MaterialDTO;
import com.institucional.dto.ActividadDTO;
import com.institucional.dto.EmpleadoDTO;
import com.institucional.dto.ProveedorDTO;
import com.movimiento.dto.LoteDTO;
import com.movimiento.dto.MovSalidaDTO;

@ManagedBean
@SessionScoped
public class DialogForm2 {

	private ArticuloDTO articulo;

	private Integer codArticulo;

	private Float cantidad;

	private String cuit;

	private Integer actividad;

	private String empleado;

	private String codLote;

	private String nroComprobante;
	
	private Integer mat;
	
	
	public Integer getMat() {
		return mat;
	}

	public void setMat(Integer mat) {
		this.mat = mat;
	}

	public String getNroComprobante() {
		return nroComprobante;
	}

	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	public String getCodLote() {
		return codLote;
	}

	public void setCodLote(String codLote) {
		this.codLote = codLote;
	}

	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	public Integer getActividad() {
		return actividad;
	}

	public void setActividad(Integer actividad) {
		this.actividad = actividad;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public Float getCantidad() {
		return cantidad;
	}

	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}

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
	
	public void seleccionarFilaM(MaterialDTO mate) throws IOException {
		
		System.out.println(mate.getCodMaterial());
		
		this.setMat(mate.getCodMaterial());

		
		System.out.println(mat);
				
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect(ec.getRequestContextPath() + "/general/dialog2.xhtml");

	}

	public void seleccionarFila2(ArticuloDTO art) throws IOException {

		this.codArticulo = art.getCodArticulo();

		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect(ec.getRequestContextPath() + "/general/dialog2.xhtml");

	}

	public void seleccionarFilaP(ProveedorDTO pro) throws IOException {

		this.cuit = pro.getCuit();

		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect(ec.getRequestContextPath() + "/general/dialog2.xhtml");

	}

	public void seleccionarFilaAct(ActividadDTO act) throws IOException {

		this.actividad = act.getNroActividad();

		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect(ec.getRequestContextPath() + "/general/dialog2.xhtml");

	}

	public void seleccionarFilaMov(MovSalidaDTO mos) throws IOException {

		this.nroComprobante = mos.getId();

		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect(ec.getRequestContextPath() + "/general/dialog2.xhtml");

	}

	public void seleccionarFilaEmp(EmpleadoDTO emp) throws IOException {

		this.empleado = emp.getLegajo();

		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect(ec.getRequestContextPath() + "/general/dialog2.xhtml");

	}

	public void seleccionarFilaLot(LoteDTO lot) throws IOException {

		this.codLote = lot.getCodLote();

		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect(ec.getRequestContextPath() + "/general/dialog2.xhtml");

	}

}
