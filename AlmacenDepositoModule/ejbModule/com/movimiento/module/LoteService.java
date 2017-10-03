package com.movimiento.module;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;


import com.articulo.entities.Articulo;
import com.articulo.repository.ArticuloRepository;
import com.institucional.entities.Almacen;
import com.institucional.module.ActividadService;
import com.institucional.repository.AlmacenRepository;
import com.movimiento.dto.DetMovEntradaDTO;
import com.movimiento.dto.LoteDTO;
import com.movimiento.dto.LoteFactory;
import com.movimiento.dto.MovEntradaDTO;
import com.movimiento.entities.EstadoLote;
import com.movimiento.entities.Lote;
import com.movimiento.entities.Modelo;
import com.movimiento.repository.EstadoLoteRepository;
import com.movimiento.repository.LoteRepository;
import com.movimiento.repository.ModeloRepository;

/**
 * Session Bean implementation class LoteService
 */
@Stateless
@LocalBean
public class LoteService implements LoteServiceRemote {
	@EJB
	private LoteRepository lr;
	
	@EJB
	private AlmacenRepository almRepository;
	
	@EJB
	private ModeloRepository modRepository;
	
	@EJB
	private ArticuloRepository artRepository;
	
	@EJB
	private AlmacenRepository aRepository;
	
	@EJB
	private ActividadService actService;
	
	@EJB
	private EstadoLoteRepository elRepository;
    /**
     * Default constructor. 
     */
    public LoteService() {
        // TODO Auto-generated constructor stub
    }
    
	/** 
	 * 
	 * Crear Lote para un conjunto de 
	 * articulo del movimiento de entrada
	 * 
	 * */
    
    public Lote addLote(DetMovEntradaDTO ddto, MovEntradaDTO movEntrada) {
		//List<ValidationError> errors = validator.validarAddProducto(producto);
		/*if (errors.size() > 0) {
			throw new BusinessException("Errores al agregar el producto.", errors);
		}
		 */
    	System.out.println(movEntrada.getAlmacen());
    	
    	Modelo mod = modRepository.get(ddto.getModelo());
		
		Articulo art = artRepository.get(ddto.getCodArticulo());
				
		Lote l = new Lote();
		l.setFechaVto(ddto.getFechaVto());
		l.setModelo(mod);
		l.setStockActual(ddto.getCantidad());
		l.setStockInicial(ddto.getCantidad());
		l.setImporteUnitario(ddto.getImporteUnitario());
		l.setArticulo(art);
		l.setActividad(actService.getActividad(movEntrada.getActividad()));
		l.setFechaCreado(movEntrada.getFechaIngreso());
				
		this.addLoteAlmacen(movEntrada.getAlmacen(), l, ddto.getCantidad(), ddto.getUbicacion());
		
		this.addHistorialEstadoLote(1, l, movEntrada.getUsuario());
		
		
		lr.add(l);
						
		return l;
	}
    
    
    public Collection<LoteDTO> listAll() {
    	
		return LoteFactory.getLoteDTO(lr.getAll());
	}
    
    public void addLoteAlmacen(int idAlmacen, Lote lote, Float cantidad, String ubicacion) {
    	

		Almacen a = aRepository.get(idAlmacen);
		Lote l = lote;
		
		
		l.addLoteAlmacenes(a, cantidad, ubicacion);
		
	}

    @Override
	public LoteDTO findById(String id) {
		// TODO Auto-generated method stub
		return LoteFactory.getLoteDTO(lr.get(id));
	}
    
    public void addHistorialEstadoLote(int idEstado, Lote lote, String usuario) {
    	

		EstadoLote el = elRepository.get(idEstado);
		Lote l = lote;
		l.addHistorialEstadoLote(el, usuario);
	}

}
