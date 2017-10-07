package com.movimiento;

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

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.institucional.dto.AlmacenDTO;
import com.institucional.module.AlmacenServiceRemote;
import com.movimiento.dto.DetMovEntradaDTO;
import com.movimiento.dto.DetMovSalidaDTO;
import com.movimiento.dto.LoteAlmacenDTO;
import com.movimiento.dto.LoteDTO;
import com.movimiento.dto.MovEntradaDTO;
import com.movimiento.dto.MovSalidaDTO;
import com.movimiento.module.MovAjustarServiceRemote;

@ManagedBean(name = "movAjustar")
@SessionScoped

public class MovAjustar implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	@EJB
	private MovAjustarServiceRemote movAjustarService;
	@EJB
	private AlmacenServiceRemote almacenService;

	private MovEntradaDTO movAjustar;
	private MovSalidaDTO movAjustarFaltante;
	private LoteDTO lote;
	private String codLote;
	private String bl;
	private String ae;
	private String af;
	private String ce;
	private List<DetMovEntradaDTO> detlallesMovAjustar;
	private List<DetMovSalidaDTO> detallesMovAjustarFaltante;
	private String almacen;
	private List<String> optionsAlm;
	Collection<AlmacenDTO> listALM = new ArrayList<AlmacenDTO>();
	AlmacenDTO almacenDTO;

	public MovEntradaDTO getMovAjustar() {
		return movAjustar;
	}

	public void setMovAjustar(MovEntradaDTO movAjustar) {
		this.movAjustar = movAjustar;
	}

	public LoteDTO getLote() {
		return lote;
	}

	public void setLote(LoteDTO lote) {
		this.lote = lote;
	}

	public String getCodLote() {
		return codLote;
	}

	public void setCodLote(String codLote) {
		this.codLote = codLote;
	}

	public String getBl() {
		return bl;
	}

	public void setBl(String bl) {
		this.bl = bl;
	}

	public String getAe() {
		return ae;
	}

	public void setAe(String ae) {
		this.ae = ae;
	}

	public String getAf() {
		return af;
	}

	public void setAf(String af) {
		this.af = af;
	}

	public String getCe() {
		return ce;
	}

	public void setCe(String ce) {
		this.ce = ce;
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

	public Collection<AlmacenDTO> getAlmacenes() {
		return almacenService.listAll();
	}

	public List<DetMovEntradaDTO> getDetlallesMovAjustar() {
		return detlallesMovAjustar;
	}

	public void setDetlallesMovAjustar(List<DetMovEntradaDTO> detlallesMovAjustar) {
		this.detlallesMovAjustar = detlallesMovAjustar;
	}

	public MovSalidaDTO getMovAjustarFaltante() {
		return movAjustarFaltante;
	}

	public void setMovAjustarFaltante(MovSalidaDTO movAjustarFaltante) {
		this.movAjustarFaltante = movAjustarFaltante;
	}

	public List<DetMovSalidaDTO> getDetlallesMovAjustarFaltante() {
		return detallesMovAjustarFaltante;
	}

	public void setDetlallesMovAjustarFaltante(List<DetMovSalidaDTO> detlallesMovAjustarFaltante) {
		this.detallesMovAjustarFaltante = detlallesMovAjustarFaltante;
	}

	public String buscarLote() {

		bl = "Hello world!";

		try {

			for (AlmacenDTO al : listALM) {

				if (al.getNomAlmacen().equals(almacen)) {

					almacenDTO = al;

				}
			}

			lote = movAjustarService.buscarLotes(codLote);

			for (LoteAlmacenDTO la : lote.getLoteAlmacenDTO()) {

				if (la.getAlmacenId() == almacenDTO.getCodAlmacen()) {

					lote.setStockAlmacen(la.getCantidad());

				}
			}

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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error inesperado del sistema. No se pudo agregar el Movimiento de Salida.", e.getMessage()));
		}

		return null;
	}

	public String Detalle() {

		ae = null;
		af = null;
		ce = null;

		try {

			if (lote.getStockInicial() >= lote.getStockReal() && lote.getStockReal() >= 0) {

				if (lote.getStockAlmacen() < lote.getStockReal()) {

					ae = "Crear Movimiento Entrada";

					movAjustar = new MovEntradaDTO();

					detlallesMovAjustar = new ArrayList<DetMovEntradaDTO>();

					DetMovEntradaDTO detMovAjustar = new DetMovEntradaDTO();

					detMovAjustar.setCantidad(lote.getStockReal() - lote.getStockAlmacen());
					detMovAjustar.setCodArticulo(lote.getArticuloID());
					detMovAjustar.setLote(lote.getCodLote());
					detMovAjustar.setImporteUnitario(lote.getImporteUnitario());
					detMovAjustar.setMarca(lote.getMarca());
					detMovAjustar.setMarString(lote.getMarString());
					detMovAjustar.setModelo(lote.getModelo());
					detMovAjustar.setModString(lote.getModString());
					detMovAjustar.setNomArticulo(lote.getArticulo());

					detlallesMovAjustar.add(detMovAjustar);

					movAjustar.setActividad(lote.getActividadID());
					movAjustar.setActString(lote.getActividad());
					movAjustar.setAlmacen(almacenDTO.getCodAlmacen());
					movAjustar.setAlmString(almacenDTO.getNomAlmacen());
					movAjustar.setCodTipMovOrigen(5);
					movAjustar.setComprobante(5);
					movAjustar.setNroSolicitud(0);
					movAjustar.setListDetalleDTO(detlallesMovAjustar);
					/*
					 * se agrega para que no de errores de nulos en la base de
					 * datos
					 */
					Date date = new Date();
					movAjustar.setFechaMO(date);
					movAjustar.setNroComprobante("no posee");

				} else if (lote.getStockAlmacen() > lote.getStockReal()) {

					af = "Crear Movimiento Salida Faltante";

					movAjustarFaltante = new MovSalidaDTO();

					detallesMovAjustarFaltante = new ArrayList<DetMovSalidaDTO>();

					DetMovSalidaDTO detMovAjustarFaltante = new DetMovSalidaDTO();

					detMovAjustarFaltante.setCantidad(lote.getStockAlmacen() - lote.getStockReal());
					detMovAjustarFaltante.setCodArticulo(lote.getArticuloID());
					detMovAjustarFaltante.setLote(lote.getCodLote());
					detMovAjustarFaltante.setImporteUnitario(lote.getImporteUnitario());
					detMovAjustarFaltante.setMarca(lote.getMarca());
					detMovAjustarFaltante.setMarString(lote.getMarString());
					detMovAjustarFaltante.setModelo(lote.getModelo());
					detMovAjustarFaltante.setModString(lote.getModString());
					detMovAjustarFaltante.setNomArticulo(lote.getArticulo());

					detallesMovAjustarFaltante.add(detMovAjustarFaltante);

					movAjustarFaltante.setNomActividad(lote.getActividad());
					movAjustarFaltante.setActividad(lote.getActividadID());
					movAjustarFaltante.setAlmacen(almacenDTO.getCodAlmacen());
					movAjustarFaltante.setNomAlmacen(almacenDTO.getNomAlmacen());
					movAjustarFaltante.setCodTipMovOrigen(6);
					movAjustarFaltante.setComprobante(6);
					movAjustarFaltante.setNroSolicitud(0);
					movAjustarFaltante.setDetalleMS(detallesMovAjustarFaltante);
					/*
					 * se agrega para que no de errores de nulos en la base de
					 * datos
					 */
					
					movAjustarFaltante.setNroComprobante("no posee");

				} 	else {
					
					System.out.println(lote.getActividad());

					ce = "Desajustar Lote";
					
					System.out.println(lote.getActividad());
									

				}

			} else {

				throw new BusinessException("La cantidad ajustada no puede ser mayor que la cantidad inicial o menor a 0 del lote: "
						+ lote.getCodLote());

			}

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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error inesperado del sistema. No se pudo agregar el Movimiento de Ajuste.", e.getMessage()));
		}

		return null;

	}

	// ingresar validador remoto

	// this.detallesMovAjustar.add(detMovAjustar);

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Cantidad Editada", ((LoteDTO) event.getObject()).getCodLote());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edición Cancelada", ((LoteDTO) event.getObject()).getCodLote());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed",
					"Viejo: " + oldValue + ", Nuevo:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	public String crearMovimiento() {

		Integer err = 0;

		try {

			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
			

			if (ae != null) {
				
				movAjustar.setUsuario(request.getUserPrincipal().getName());

				movAjustarService.crearMovAjustar(movAjustar);

			} else if (af != null) {
				
				movAjustarFaltante.setUsuario(request.getUserPrincipal().getName());
				
				movAjustarService.crearMovAjustarSalida(movAjustarFaltante);
				
			} else if (ce != null) {
				
				String usuario;
				
				usuario=request.getUserPrincipal().getName();
				
				movAjustarService.crearMovAjustarDesajuste(lote,usuario);
				
			}

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

			System.out.println("List<ValidationError> :" + e);
		}

		// FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		//

		if (err == 0) {
			// System.out.println("FIN");

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("movAjustar", null);

			if (ae != null) {

				return "/reportes/movEntrada.xhtml";

			} else if (af != null) {

				return "/reportes/movSalida.xhtml";

			}  else if (ce != null) {
				
				return "/general/lote.xhtml";
				
			}

		}

		// return "/index.xhtml?faces-redirect=true";
		return null;

	}

	public String cancelar() {

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "/index.xhtml";
	}

	@PostConstruct
	private void initialize() {

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
