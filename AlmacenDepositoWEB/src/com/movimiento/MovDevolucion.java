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

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.movimiento.dto.DetMovEntradaDTO;
import com.movimiento.dto.DetMovSalidaDTO;
import com.movimiento.dto.MovEntradaDTO;
import com.movimiento.dto.MovSalidaDTO;
import com.movimiento.module.MovDevolucionServiceRemote;

@ManagedBean(name = "movDevolucion")
@SessionScoped

public class MovDevolucion implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	@EJB
	private MovDevolucionServiceRemote movDevolucionService;

	private MovEntradaDTO movDevolucion;
	private MovSalidaDTO movSalida;
	private String bl;
	private String bl2;
	private List<DetMovEntradaDTO> detlallesMovDevolucion;

	public MovEntradaDTO getMovDevolucion() {
		return movDevolucion;
	}

	public void setMovDevolucion(MovEntradaDTO movDevolucion) {
		this.movDevolucion = movDevolucion;
	}

	public MovSalidaDTO getMovSalida() {
		return movSalida;
	}

	public void setMovSalida(MovSalidaDTO movSalida) {
		this.movSalida = movSalida;
	}

	public String getBl() {
		return bl;
	}

	public void setBl(String bl) {
		this.bl = bl;
	}

	public String getBl2() {
		return bl2;
	}

	public void setBl2(String bl2) {
		this.bl2 = bl2;
	}

	

	public List<DetMovEntradaDTO> getDetlallesMovDevolucion() {
		return detlallesMovDevolucion;
	}

	public void setDetlallesMovDevolucion(List<DetMovEntradaDTO> detlallesMovDevolucion) {
		this.detlallesMovDevolucion = detlallesMovDevolucion;
	}

	public String buscarMovSalida() {

		bl = "Hello world!";

		try {

			if (movSalida == null) {

				movSalida = new MovSalidaDTO();

			}

			movSalida = movDevolucionService.buscarMovSalida(movDevolucion.getNroComprobante());

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

		bl2 = "Hello world!";

		try {

			if (movDevolucion == null) {
				movDevolucion = new MovEntradaDTO();
			}

			detlallesMovDevolucion = new ArrayList<DetMovEntradaDTO>();

			for (DetMovSalidaDTO ddto : movSalida.getDetalleMS()) {

				DetMovEntradaDTO detMovDevolucion = new DetMovEntradaDTO();

				if (ddto.getCantDevolucion() != null && ddto.getCantDevolucion() > 0) {

					if (ddto.getCantidad() >= ddto.getCantDevolucion()) {

						detMovDevolucion.setCantidad(ddto.getCantDevolucion());
						detMovDevolucion.setCodArticulo(ddto.getCodArticulo());
						detMovDevolucion.setLote(ddto.getLote());
						detMovDevolucion.setImporteUnitario(ddto.getImporteUnitario());
						detMovDevolucion.setMarca(ddto.getMarca());
						detMovDevolucion.setMarString(ddto.getMarString());
						detMovDevolucion.setModelo(ddto.getModelo());
						detMovDevolucion.setModString(ddto.getModString());
						detMovDevolucion.setNomArticulo(ddto.getNomArticulo());
						detMovDevolucion.setNroSerie_Proveedor(ddto.getNroSerie_Proveedor());

						detlallesMovDevolucion.add(detMovDevolucion);

					} else {

						throw new BusinessException(
								"La cantidad devuelta no puede ser mayor que la cantidad del movimiento salida en el lote: "
										+ ddto.getLote());

					}

				}

			}
			
			movDevolucion.setActividad(movSalida.getActividad());
			movDevolucion.setActString(movSalida.getNomActividad());
			movDevolucion.setAlmacen(movSalida.getAlmacen());
			movDevolucion.setAlmString(movSalida.getNomAlmacen());
			movDevolucion.setCodTipMovOrigen(4);
			movDevolucion.setComprobante(4);
			movDevolucion.setNroSolicitud(0);
			movDevolucion.setFechaMO(movSalida.getFechaSalida());
			movDevolucion.setListDetalleDTO(detlallesMovDevolucion);
			
			
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
					"Error inesperado del sistema. No se pudo agregar el Movimiento de Devolución.", e.getMessage()));
		}

		return null;

	}

	// ingresar validador remoto

	// this.detallesMovDevolucion.add(detMovDevolucion);

	

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Cantidad Editada", ((DetMovSalidaDTO) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edición Cancelada", ((DetMovSalidaDTO) event.getObject()).getId());
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
			movDevolucion.setUsuario(request.getUserPrincipal().getName());
			
			movDevolucionService.crearMovDevolucion(movDevolucion);
			
			
	


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

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("movDevolucion", null);

			return "/reportes/movEntrada.xhtml";

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

		if (movDevolucion == null) {
			movDevolucion = new MovEntradaDTO();
			// detallesMovDevolucion = new ArrayList<DetMovDevolucionDTO>();
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
