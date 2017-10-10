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
import com.institucional.dto.AlmacenDTO;
import com.institucional.module.AlmacenServiceRemote;
import com.movimiento.dto.DetMovSalidaDTO;
import com.movimiento.dto.LoteDTO;
import com.movimiento.dto.MovSalidaDTO;
import com.movimiento.module.MovTrasladoSalidaServiceRemote;

@ManagedBean(name = "movTrasladoSalida")
@SessionScoped
public class MovTrasladoSalida implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private MovTrasladoSalidaServiceRemote movTrasladoSalidaService;
	@EJB
	private AlmacenServiceRemote almacenService;

	private MovSalidaDTO movTrasladoSalida;
	private Collection<DetMovSalidaDTO> detallesMovTrasladoSalida;
	private Collection<LoteDTO> lotes;
	private String bl;
	private List<String> optionsAlm;
	private Collection<AlmacenDTO> listALM = new ArrayList<AlmacenDTO>();
	private AlmacenDTO almacenDTO;
	private String almacenOrigen;
	private String almacenDestino;

	private boolean nuevo = true;

	private Float cantidad;
	private String codLote;

	public MovSalidaDTO getMovTrasladoSalida() {
		return movTrasladoSalida;
	}

	public void setMovTrasladoSalida(MovSalidaDTO movTrasladoSalida) {
		this.movTrasladoSalida = movTrasladoSalida;
	}

	public Collection<DetMovSalidaDTO> getDetallesMovTrasladoSalida() {
		return detallesMovTrasladoSalida;
	}

	public void setDetallesMovEntrada(Collection<DetMovSalidaDTO> detallesMovTrasladoSalida) {
		this.detallesMovTrasladoSalida = detallesMovTrasladoSalida;
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

	public String getCodLote() {
		return codLote;
	}

	public void setCodLote(String codLote) {
		this.codLote = codLote;
	}

	public Collection<LoteDTO> getBuscarLotes() {
		return lotes;
	}

	public void setBuscarLotes(Collection<LoteDTO> buscarLotes) {
		this.lotes = buscarLotes;
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

	public String getAlmacenOrigen() {
		return almacenOrigen;
	}

	public void setAlmacenOrigen(String almacenOrigen) {
		this.almacenOrigen = almacenOrigen;
	}

	public String getAlmacenDestino() {
		return almacenDestino;
	}

	public void setAlmacenDestino(String almacenDestino) {
		this.almacenDestino = almacenDestino;
	}

	public Collection<AlmacenDTO> getAlmacenes() {
		return almacenService.listAll();
	}

	public void addDetalle() {

		DetMovSalidaDTO detMovTrasladoSalida = new DetMovSalidaDTO();
		detMovTrasladoSalida.setCantidad(cantidad);
		detMovTrasladoSalida.setLote(codLote);

		// ingresar validador remoto

		this.detallesMovTrasladoSalida.add(detMovTrasladoSalida);

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
			
			if(almacenDestino.equals(almacenOrigen)){
				
				throw new BusinessException(
						"El almacen Origen y Destino no pueden ser los mismos");
				
			}

			if (lotes == null) {

				lotes = new ArrayList<LoteDTO>();

			} 
			
			for (AlmacenDTO al : listALM) {

				if (al.getNomAlmacen().equals(almacenOrigen)) {

					almacenDTO = al;

				}
			}
			
			movTrasladoSalida.setAlmacen(almacenDTO.getCodAlmacen());
			movTrasladoSalida.setNomAlmacen(almacenDTO.getNomAlmacen());
			
			lotes = movTrasladoSalidaService.buscarLotes(movTrasladoSalida, detallesMovTrasladoSalida);

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
			movTrasladoSalida.setUsuario(request.getUserPrincipal().getName());

			List<DetMovSalidaDTO> detalles = new ArrayList<DetMovSalidaDTO>();

			for (LoteDTO l : lotes) {

				DetMovSalidaDTO d = new DetMovSalidaDTO();

				d.setCantidad(l.getCantidad());
				d.setLoteId(l.getCodLote());
				//System.out.println(l.isAjustar());
				d.setAjustar(l.isAjustar());
				detalles.add(d);
				
				movTrasladoSalida.setActividad(l.getActividadID());

			}

			movTrasladoSalida.setDetalleMS(detalles);
			
			String observaciones = movTrasladoSalida.getObservaciones();
			
			movTrasladoSalida.setObservaciones("Almacen Destino: "+ almacenDestino +". " + observaciones);
			
			movTrasladoSalida.setNroComprobante(almacenDestino);

		    movTrasladoSalidaService.crearMovTrasladoSalida(movTrasladoSalida);

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
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("movTrasladoSalida", null);

			return "/reportes/movSalida.xhtml";

		}

		return null;

	}

	public String borrarFila(DetMovSalidaDTO detalle) {

		this.detallesMovTrasladoSalida.remove(detalle);

		return null;

	}

	public String borrarFilaLote(LoteDTO lote) {

		this.lotes.remove(lote);

		return null;

	}

	public String cancelar() {

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "/index.xhtml"; // agregar movTrasladoSalida
	}

	@PostConstruct
	private void initialize() {

		if (movTrasladoSalida == null) {
			movTrasladoSalida = new MovSalidaDTO();
			detallesMovTrasladoSalida = new ArrayList<DetMovSalidaDTO>();
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
