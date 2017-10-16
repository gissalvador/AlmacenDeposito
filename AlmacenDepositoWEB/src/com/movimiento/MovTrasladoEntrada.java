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
import com.institucional.dto.AlmacenDTO;
import com.movimiento.dto.DetMovEntradaDTO;
import com.movimiento.dto.DetMovSalidaDTO;
import com.movimiento.dto.LoteDTO;
import com.movimiento.dto.MovEntradaDTO;
import com.movimiento.dto.MovSalidaDTO;
import com.movimiento.module.MovTrasladoEntradaServiceRemote;

@ManagedBean(name = "movTrasladoEntrada")
@SessionScoped

public class MovTrasladoEntrada implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	@EJB
	private MovTrasladoEntradaServiceRemote movTrasladoEntradaService;

	private MovEntradaDTO movTrasladoEntrada;
	private MovSalidaDTO movSalida;
	private String bl;
	private String bl2;
	private List<DetMovEntradaDTO> detallesMovTrasladoEntrada;
	

	public MovEntradaDTO getMovTrasladoEntrada() {
		return movTrasladoEntrada;
	}

	public void setMovTrasladoEntrada(MovEntradaDTO movTrasladoEntrada) {
		this.movTrasladoEntrada = movTrasladoEntrada;
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

	public String ajustar(DetMovEntradaDTO buslote) {

		if (buslote.isAjustar()) {

			return "Ajustar Lote";

		} else {

			return "Entrada Lote";

		}

	}

	public String getBl2() {
		return bl2;
	}

	public void setBl2(String bl2) {
		this.bl2 = bl2;
	}

	public List<DetMovEntradaDTO> getDetlallesMovTrasladoEntrada() {
		return detallesMovTrasladoEntrada;
	}

	public void setDetlallesMovTrasladoEntrada(List<DetMovEntradaDTO> detlallesMovTrasladoEntrada) {
		this.detallesMovTrasladoEntrada = detlallesMovTrasladoEntrada;
	}

	public String buscarMovSalida() {

		bl = "Hello world!";

		try {

			if (movSalida == null) {

				movSalida = new MovSalidaDTO();

			}

			movSalida = movTrasladoEntradaService.buscarMovSalida(movTrasladoEntrada.getNroComprobante());

			AlmacenDTO almacen = movTrasladoEntradaService.buscarAlmacen(movSalida.getNroComprobante());

			movTrasladoEntrada.setAlmacen(almacen.getCodAlmacen());
			movTrasladoEntrada.setAlmString(almacen.getNomAlmacen());

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

			if (movTrasladoEntrada == null) {
				movTrasladoEntrada = new MovEntradaDTO();
			}
			
			
			detallesMovTrasladoEntrada = new ArrayList<DetMovEntradaDTO>();

			for (DetMovSalidaDTO ddto : movSalida.getDetalleMS()) {

					
				if(ddto.isAjustar()==true){
					
					System.out.println(ddto.getUbicacion());
					
				} else {
					
					if(ddto.getUbicacion()==null){
						
						System.out.println("true");
						
						throw new BusinessException(
								"La ubicación no puede ser nula en el lote: "
										+ ddto.getLote());

						
					}
					
				}
				
				DetMovEntradaDTO detMovTrasladoEntrada = new DetMovEntradaDTO();

				detMovTrasladoEntrada.setCantidad(ddto.getCantidad());
				detMovTrasladoEntrada.setCodArticulo(ddto.getCodArticulo());
				detMovTrasladoEntrada.setLote(ddto.getLote());
				detMovTrasladoEntrada.setImporteUnitario(ddto.getImporteUnitario());
				detMovTrasladoEntrada.setMarca(ddto.getMarca());
				detMovTrasladoEntrada.setMarString(ddto.getMarString());
				detMovTrasladoEntrada.setModelo(ddto.getModelo());
				detMovTrasladoEntrada.setModString(ddto.getModString());
				detMovTrasladoEntrada.setNomArticulo(ddto.getNomArticulo());
				detMovTrasladoEntrada.setNroSerie_Proveedor(ddto.getNroSerie_Proveedor());
				detMovTrasladoEntrada.setUbicacion(ddto.getUbicacion());
				detMovTrasladoEntrada.setAjustar(ddto.isAjustar());

				detallesMovTrasladoEntrada.add(detMovTrasladoEntrada);

			}

			movTrasladoEntrada.setActividad(movSalida.getActividad());
			movTrasladoEntrada.setActString(movSalida.getNomActividad());
			movTrasladoEntrada.setCodTipMovOrigen(8);
			movTrasladoEntrada.setComprobante(8);
			movTrasladoEntrada.setNroSolicitud(0);
			movTrasladoEntrada.setFechaMO(movSalida.getFechaSalida());
			movTrasladoEntrada.setListDetalleDTO(detallesMovTrasladoEntrada);

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
			movTrasladoEntrada.setUsuario(request.getUserPrincipal().getName());

			movTrasladoEntradaService.crearMovTrasladoEntrada(movTrasladoEntrada);

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

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("movTrasladoEntrada", null);

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

		if (movTrasladoEntrada == null) {
			movTrasladoEntrada = new MovEntradaDTO();
			// detallesMovTrasladoEntrada = new
			// ArrayList<DetMovTrasladoEntradaDTO>();
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
