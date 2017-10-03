package com.movimiento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
import com.movimiento.module.MovSalidaServiceRemote;

@ManagedBean(name = "movSalida")
@SessionScoped
public class MovSalida implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private MovSalidaServiceRemote movSalidaService;

	private MovSalidaDTO movSalida;
	private Collection<DetMovSalidaDTO> detallesMovSalida;
	private Collection<LoteDTO> lotes;
	private String bl;

	private boolean nuevo = true;

	private Float cantidad;
	private Integer codArticulo;

	public MovSalidaDTO getMovSalida() {
		return movSalida;
	}

	public void setMovSalida(MovSalidaDTO movSalida) {
		this.movSalida = movSalida;
	}

	public Collection<DetMovSalidaDTO> getDetallesMovSalida() {
		return detallesMovSalida;
	}

	public void setDetallesMovEntrada(Collection<DetMovSalidaDTO> detallesMovSalida) {
		this.detallesMovSalida = detallesMovSalida;
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

	public Collection<LoteDTO> getBuscarLotes() {
		return lotes;
	}

	public void setBuscarLotes(Collection<LoteDTO> buscarLotes) {
		this.lotes = buscarLotes;
	}

	public void addDetalle() {

		DetMovSalidaDTO detMovSalida = new DetMovSalidaDTO();
		detMovSalida.setCantidad(cantidad);
		detMovSalida.setCodArticulo(codArticulo);

		// ingresar validador remoto

		this.detallesMovSalida.add(detMovSalida);

	}

	public String getBl() {
		return bl;
	}

	public void setBl(String bl) {
		this.bl = bl;
	}

	public String ajustar(LoteDTO buslote) {

		if (buslote.isAjustar()) {

			return "Ajustar Lote";

		} else {

			return "Salida Lote";

		}

	}

	public String buscarLote() {

		bl = "Hello world!";

		try {

			if (lotes == null) {

				lotes = new ArrayList<LoteDTO>();

			} 

			lotes = movSalidaService.buscarLotes(movSalida, detallesMovSalida);

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
			movSalida.setUsuario(request.getUserPrincipal().getName());

			List<DetMovSalidaDTO> detalles = new ArrayList<DetMovSalidaDTO>();

			for (LoteDTO l : lotes) {

				DetMovSalidaDTO d = new DetMovSalidaDTO();

				d.setCantidad(l.getCantidad());
				d.setLoteId(l.getCodLote());
				//System.out.println(l.isAjustar());
				d.setAjustar(l.isAjustar());
				detalles.add(d);

			}

			movSalida.setDetalleMS(detalles);

			movSalidaService.crearMovSalida(movSalida);

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
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("movSalida", null);

			return "/reportes/movSalida.xhtml";

		}

		return null;

	}

	public String borrarFila(DetMovSalidaDTO detalle) {

		this.detallesMovSalida.remove(detalle);

		return null;

	}

	public String borrarFilaLote(LoteDTO lote) {

		this.lotes.remove(lote);

		return null;

	}

	public String cancelar() {

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "/index.xhtml"; // agregar movSalida
	}

	@PostConstruct
	private void initialize() {

		if (movSalida == null) {
			movSalida = new MovSalidaDTO();
			detallesMovSalida = new ArrayList<DetMovSalidaDTO>();
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
