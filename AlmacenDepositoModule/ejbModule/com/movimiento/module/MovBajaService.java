package com.movimiento.module;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.institucional.entities.Actividad;
import com.institucional.module.ActividadService;
import com.institucional.repository.AlmacenRepository;
import com.movimiento.dto.DetMovSalidaDTO;
import com.movimiento.dto.LoteDTO;
import com.movimiento.dto.LoteFactory;
import com.movimiento.dto.MovSalidaDTO;
import com.movimiento.entities.DetalleMovEntrada;
import com.movimiento.entities.DetalleMovSalida;
import com.movimiento.entities.Lote;
import com.movimiento.entities.MovOrigen;
import com.movimiento.entities.MovSalida;
import com.movimiento.repository.DetalleMERepository;
import com.movimiento.repository.DetalleMSrepository;
import com.movimiento.repository.LoteRepository;
import com.movimiento.repository.MovSalidaRepository;

/**
 * Session Bean implementation class MovBajaService
 */
@Stateless
@LocalBean
public class MovBajaService implements MovBajaServiceRemote {
	
	
	@EJB
	private LoteRepository lRepository;
	
	@EJB
	private LoteService lservice;
	
	@EJB
	private DetalleMERepository detentradaRepository;
	
	@EJB
	private DetalleMSrepository detRepository;
	
	@EJB
	private MovOrigenService mos;
	
	@EJB
	private ActividadService aService;
	
	@EJB
	private AlmacenRepository almRepository;
	
	@EJB
	private MovSalidaRepository msRepository;
	
	@EJB
	private MovBajaValidations validator;

    /**
     * Default constructor. 
     */
    public MovBajaService() {
        // TODO Auto-generated constructor stub
    }
    
    
    @Override
	public LoteDTO buscarLotes(MovSalidaDTO movBaja, String codLote) {
    	
    		
    	List<ValidationError> errors = validator.validaraddMovBaja(movBaja, codLote);

		if (errors.size() > 0) {
			throw new BusinessException("Errores al agregar el Movimiento de Baja.", errors);
		}
		
		   	    	    	
    	    	
        	
    	Lote lote = lRepository.get(codLote);
    	
    	LoteDTO loteDTO = new LoteDTO();
    	
    	loteDTO = LoteFactory.getLoteDTO(lote);
    	    	
    	return loteDTO;
    	
    	    
    }


	@Override
	public void crearMovBaja(MovSalidaDTO movBaja) {
		// TODO Auto-generated method stub
		
		Float importetotal = (float) 0;

		List<DetalleMovSalida> detList;

		detList = new ArrayList<DetalleMovSalida>();
		
		for (DetMovSalidaDTO ddto : movBaja.getDetalleMS()) {
		
			Lote lote = lRepository.get(ddto.getLoteId());
			
			lservice.addHistorialEstadoLote(4, lote, movBaja.getUsuario());
			
			DetalleMovSalida dms = addDetalleMovSalida(ddto.getCantidad(), lote);
	
			importetotal += dms.getImporteUnitarioMS() * dms.getCantidadMS();
	
			detList.add(dms);
		
		}
		
		if (movBaja.getDetalleMS().size() > 0) {

			movBaja.setImporteTotal(importetotal);

			addMovSalida(movBaja, detList);
		}
		
	}
	
	
	public DetalleMovSalida addDetalleMovSalida(float cant, Lote l) {

		String np = null;
		Float iu = null;

		DetalleMovSalida dms = new DetalleMovSalida();

		List<String> ls = detentradaRepository.buscarxLote(l.getCodLote());

		for (String s : ls) {

			DetalleMovEntrada dme = new DetalleMovEntrada();

			dme = detentradaRepository.get(s);

			np = dme.getNroSerie_Proveedor();
			iu = dme.getImporteUnitarioME();

			break;

		}

		dms.setCantidadMS(cant);
		dms.setImporteUnitarioMS(iu);
		dms.setNroSerie_Proveedor(np);
		dms.setLote(l);

		detRepository.add(dms);

		return dms;
	}
    
	
	public MovSalida addMovSalida(MovSalidaDTO movSalida, List<DetalleMovSalida> detList) {

		MovOrigen mo = mos.addMovOrigen(movSalida, 3);

		Actividad act = aService.getActividad(movSalida.getActividad());

		MovSalida movSalidaNuevo = new MovSalida();

		movSalidaNuevo.setFechaMovSalida(movSalida.getFechaSalida());
		movSalidaNuevo.setObservaciones(movSalida.getObservaciones());
		movSalidaNuevo.setTotalMovSalida(movSalida.getImporteTotal());
		movSalidaNuevo.setActividad(act);
		movSalidaNuevo.setMovOrigen(mo);
		movSalidaNuevo.setDetalleME(detList);
		movSalidaNuevo.setAlmacen(almRepository.get(movSalida.getAlmacen()));
		movSalidaNuevo.setUsuario(movSalida.getUsuario());
		Date date = new Date();
		movSalidaNuevo.setFechaCreado(date);

		msRepository.add(movSalidaNuevo);

		return movSalidaNuevo;

	}
    

}
