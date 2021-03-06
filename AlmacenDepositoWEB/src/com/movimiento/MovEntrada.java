package com.movimiento;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.articulo.dto.ArticuloDTO;
import com.institucional.dto.ActividadDTO;
import com.institucional.dto.AlmacenDTO;
import com.institucional.dto.ProveedorDTO;
import com.institucional.module.AlmacenServiceRemote;
import com.movimiento.dto.MovEntradaDTO;
import com.movimiento.dto.DetMovEntradaDTO;
import com.movimiento.module.MovEntradaServiceRemote;

@ManagedBean(name = "movEntrada")
@SessionScoped

public class MovEntrada implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	@EJB
	private MovEntradaServiceRemote movEntradaService;

	@EJB
	private AlmacenServiceRemote almacenService;

	private MovEntradaDTO movEntrada;
	private Collection<DetMovEntradaDTO> detallesMovEntrada;

	private String almacen;
	private List<String> optionsAlm;
	Collection<AlmacenDTO> listALM = new ArrayList<AlmacenDTO>();
	AlmacenDTO almacenDTO;

	private boolean nuevo = true;

	private Float cantidad;
	private Integer codArticulo;
	private String ubicacion;
	private Integer modelo;
	private Integer marca;
	private Date fechaVto;
	private String nroSerie;
	private float importeUnitario;
	private String id;

	public MovEntradaDTO getMovEntrada() {
		return movEntrada;
	}

	public void setMovEntrada(MovEntradaDTO movEntrada) {
		this.movEntrada = movEntrada;
	}

	public Collection<DetMovEntradaDTO> getDetallesMovEntrada() {
		return detallesMovEntrada;
	}

	public void setDetallesMovEntrada(Collection<DetMovEntradaDTO> detallesMovEntrada) {
		this.detallesMovEntrada = detallesMovEntrada;
	}

	public MovEntradaServiceRemote getMovEntradaService() {
		return movEntradaService;
	}

	public void setMovEntradaService(MovEntradaServiceRemote movEntradaService) {
		this.movEntradaService = movEntradaService;
	}

	public Collection<AlmacenDTO> getAlmacenes() {
		return almacenService.listAll();
	}

	public List<String> getOptionsAlm() {
		return optionsAlm;
	}

	public void setOptionsAlm(List<String> optionsAlm) {
		this.optionsAlm = optionsAlm;
	}

	public Collection<AlmacenDTO> getListALM() {
		return listALM;
	}

	public void setListALM(Collection<AlmacenDTO> listALM) {
		this.listALM = listALM;
	}

	public AlmacenDTO getAlmacenDTO() {
		return almacenDTO;
	}

	public void setAlmacenDTO(AlmacenDTO almacenDTO) {
		this.almacenDTO = almacenDTO;
	}

	public String getAlmacen() {
		return almacen;
	}

	public void setAlmacen(String almacen) {
		this.almacen = almacen;
	}

	public boolean isNuevo() {
		return nuevo;
	}

	public void setNuevo(boolean nuevo) {
		this.nuevo = nuevo;
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

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Integer getModelo() {
		return modelo;
	}

	public void setModelo(Integer modelo) {
		this.modelo = modelo;
	}

	public Integer getMarca() {
		return marca;
	}

	public void setMarca(Integer marca) {
		this.marca = marca;
	}

	public Date getFechaVto() {
		return fechaVto;
	}

	public void setFechaVto(Date fechaVto) {
		this.fechaVto = fechaVto;
	}

	public String getNroSerie() {
		return nroSerie;
	}

	public void setNroSerie(String nroSerie) {
		this.nroSerie = nroSerie;
	}

	public float getImporteUnitario() {
		return importeUnitario;
	}

	public void setImporteUnitario(float importeUnitario) {
		this.importeUnitario = importeUnitario;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void seleccionarFila2(ArticuloDTO art) throws IOException {

		this.codArticulo = art.getCodArticulo();

		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect(ec.getRequestContextPath() + "/movimiento/m_entrada.xhtml");

	}

	public void seleccionarFilaP(ProveedorDTO pro) throws IOException {

		this.movEntrada.setCuitlegajo(pro.getCuit());

		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect(ec.getRequestContextPath() + "/movimiento/m_entrada.xhtml");

	}

	public void seleccionarFilaAct(ActividadDTO act) throws IOException {

		this.movEntrada.setActividad(act.getNroActividad());

		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect(ec.getRequestContextPath() + "/movimiento/m_entrada.xhtml");

	}

	public void addDetalle() {

		DetMovEntradaDTO detMovEntrada = new DetMovEntradaDTO();
		detMovEntrada.setCantidad(cantidad);
		detMovEntrada.setCodArticulo(codArticulo);
		detMovEntrada.setUbicacion(ubicacion);
		detMovEntrada.setModelo(modelo);
		detMovEntrada.setMarca(marca);
		detMovEntrada.setFechaVto(fechaVto);
		detMovEntrada.setImporteUnitario(importeUnitario);
		detMovEntrada.setNroSerie_Proveedor(nroSerie);

		// ingresar validador remoto

		this.detallesMovEntrada.add(detMovEntrada);

	}

	public String crearMovimiento() {

		Integer err = 0;

		try {

			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
			movEntrada.setUsuario(request.getUserPrincipal().getName());

			for (AlmacenDTO al : listALM) {

				if (al.getNomAlmacen().equals(almacen)) {

					movEntrada.setAlmacen(al.getCodAlmacen());
					movEntrada.setAlmString(al.getNomAlmacen());

				}
			}

			id = movEntradaService.crearMovEntrada(movEntrada, detallesMovEntrada);

			movEntrada.setId(id);

		} catch (BusinessException be) {
			processBusinessException(be);
			err = 1;
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

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error inesperado del sistema. No se pudo agregar el Movimiento de Entrada.", e.getMessage()));

			// System.out.println("List<ValidationError> :"+e);
		}

		// FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		//

		if (err == 0) {
			// System.out.println("FIN");

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("movEntrada", null);

			return "/reportes/movEntrada.xhtml";

		}

		// return "/index.xhtml?faces-redirect=true";
		return null;

	}

	public String cancelar() {

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "/index.xhtml";
	}

	public String borrarFila(DetMovEntradaDTO detalle) {

		this.detallesMovEntrada.remove(detalle);

		return null;

	}

	@PostConstruct
	private void initialize() {

		if (movEntrada == null) {
			movEntrada = new MovEntradaDTO();
			detallesMovEntrada = new ArrayList<DetMovEntradaDTO>();

			movEntrada.setCodTipMovOrigen(1);
			movEntrada.setComprobante(1);
		} else {
			nuevo = false;
		}

		listALM = this.getAlmacenes();

		optionsAlm = new ArrayList<String>();

		for (AlmacenDTO al : listALM) {

			optionsAlm.add(al.getNomAlmacen());

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
				FacesContext.getCurrentInstance().addMessage("form:" + ve.getProperty(),
						new FacesMessage(FacesMessage.SEVERITY_ERROR, ve.getError(), ve.getError()));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, be.getMessage(), be.getMessage()));
		}
	}

	/**
	 * Procesa y revisa las excepciones de negocio obtenidas.
	 * 
	 * @param cve
	 */
	private void processConstraintViolationException(ConstraintViolationException cve) {
		for (ConstraintViolation<?> v : cve.getConstraintViolations()) {
			FacesContext.getCurrentInstance().addMessage("form:" + v.getPropertyPath().toString(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, v.getMessage(), v.getMessage()));
		}
	}

}
