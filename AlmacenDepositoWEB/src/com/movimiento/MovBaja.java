package com.movimiento;

import java.io.IOException;
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
import com.institucional.dto.AlmacenDTO;
import com.institucional.dto.EmpleadoDTO;
import com.institucional.module.AlmacenServiceRemote;
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
	
	@EJB
	private AlmacenServiceRemote almacenService;

	private MovSalidaDTO movBaja;
	private LoteDTO lote;
	private String bl;
	
	private String almacen;
	private List<String> optionsAlm;
	Collection<AlmacenDTO> listALM = new ArrayList<AlmacenDTO>();
	AlmacenDTO almacenDTO;

	private boolean nuevo = true;

	private String codLote;
	
	

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

	public AlmacenDTO getAlmacenDTO() {
		return almacenDTO;
	}

	public void setAlmacenDTO(AlmacenDTO almacenDTO) {
		this.almacenDTO = almacenDTO;
	}
	
	public Collection<AlmacenDTO> getAlmacenes() {
		return almacenService.listAll();
	}

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
	
	public void seleccionarFilaEmp(EmpleadoDTO emp) throws IOException {

		
		this.movBaja.setLegajo(emp.getLegajo());
		
				
		 ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		 ec.redirect(ec.getRequestContextPath() + "/movimiento/m_baja.xhtml");
		
		
		
	}
		
		public void seleccionarFilaLot(LoteDTO lot) throws IOException {

			
			this.codLote = lot.getCodLote();
			
					
			 ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			 ec.redirect(ec.getRequestContextPath() + "/movimiento/m_baja.xhtml");
			
			
			
		}

	public String buscarLote() {

		bl = "Hello world!";

		try {
			
			
			for (AlmacenDTO al : listALM) {

				if (al.getNomAlmacen().equals(almacen)) {

					movBaja.setAlmacen(al.getCodAlmacen());
					movBaja.setNomAlmacen(al.getNomAlmacen());

				}
			}

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
