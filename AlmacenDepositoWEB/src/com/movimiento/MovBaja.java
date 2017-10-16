package com.movimiento;

import java.io.Serializable;
import java.util.ArrayList;
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
import com.movimiento.dto.DetMovSalidaDTO;
import com.movimiento.dto.LoteDTO;
import com.movimiento.dto.MovSalidaDTO;
import com.movimiento.module.MovBajaServiceRemote;


@ManagedBean(name = "movBaja")
@SessionScoped
public class MovBaja implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private MovBajaServiceRemote movBajaService;

	private MovSalidaDTO movBaja;
	private LoteDTO lote;
	private String bl;

	private boolean nuevo = true;

	private String codLote;

	public MovSalidaDTO getMovBaja() {
		return movBaja;
	}

	public void setMovBaja(MovSalidaDTO movBaja) {
		this.movBaja = movBaja;
	}

	public boolean isNuevo() {
		return nuevo;
	}

	public void setNuevo(boolean nuevo) {
		this.nuevo = nuevo;
	}
	
	
	public String getCodLote() {
		return codLote;
	}

	public void setCodLote(String codLote) {
		this.codLote = codLote;
	}

	

	public LoteDTO getLote() {
		return lote;
	}

	public void setLote(LoteDTO lote) {
		this.lote = lote;
	}

	public String getBl() {
		return bl;
	}

	public void setBl(String bl) {
		this.bl = bl;
	}

	public String buscarLote() {

		bl = "Hello world!";

		try {

			lote = movBajaService.buscarLotes(movBaja, codLote);
			

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

	public String crearMovimiento() {

		Integer err = 0;

		try {
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
			movBaja.setUsuario(request.getUserPrincipal().getName());

			List<DetMovSalidaDTO> detalles = new ArrayList<DetMovSalidaDTO>();

			DetMovSalidaDTO d = new DetMovSalidaDTO();

				d.setCantidad(lote.getStockActual());
				d.setLoteId(lote.getCodLote());
				detalles.add(d);
				
					
			movBaja.setDetalleMS(detalles);
			
			String observaciones = movBaja.getObservaciones();
			
			movBaja.setObservaciones("LOTE VENCIDO. " + observaciones);
			
			movBaja.setActividad(lote.getActividadID());

			movBajaService.crearMovBaja(movBaja);

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
					"Error inesperado del sistema. No se pudo agregar el Movimiento de Salida.", e.getMessage()));
		}

		if (err == 0) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("movBaja", null);

			return "/reportes/movSalida.xhtml";

		}

		return null;

	}


	public String cancelar() {

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "/index.xhtml"; // agregar movBaja
	}

	@PostConstruct
	private void initialize() {

		if (movBaja == null) {
			movBaja = new MovSalidaDTO();
			
		} else {
			nuevo = false;
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
