package com.movimiento.entities;

import com.articulo.entities.Articulo;
import com.institucional.entities.Actividad;
import com.institucional.entities.Almacen;
import com.movimiento.entities.Modelo;

import java.io.Serializable;
import java.lang.Float;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Lote
 *
 */
@Entity

public class Lote implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private String codLote;
	
	private Date fechaCreado;
	private Date fechaVto;
	
	private Float stockActual;
	private Float stockInicial;
	
	private Float importeUnitario;
	
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "modeloId")
	private Modelo modelo;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "loteAlmacenId")
	private List<LoteAlmacen> loteAlmacenes;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "articuloId")
	private Articulo articulo;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "loteHistorialId")
	private List<HistorialEstadoLote> historialEstadoLote;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "estadoloteId")
	private EstadoLote estadoActualLote;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "actividadId")
	private Actividad actividad;
	
	
	public Lote() {
		super();
	}   
	public String getCodLote() {
		return this.codLote;
	}

	public void setCodLote(String codLote) {
		this.codLote = codLote;
	}   
	public Date getFechaVto() {
		return this.fechaVto;
	}

	public void setFechaVto(Date fechaVto) {
		this.fechaVto = fechaVto;
	}   
	public Float getStockActual() {
		return this.stockActual;
	}

	public void setStockActual(Float stockActual) {
		this.stockActual = stockActual;
	}   
		
	public Float getImporteUnitario() {
		return importeUnitario;
	}
	public void setImporteUnitario(Float importeUnitario) {
		this.importeUnitario = importeUnitario;
	}
	public Modelo getModelo() {
		return this.modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}   
	
	public Articulo getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	
	public Date getFechaCreado() {
		return fechaCreado;
	}
	public void setFechaCreado(Date fechaCreado) {
		this.fechaCreado = fechaCreado;
	}
	public Float getStockInicial() {
		return stockInicial;
	}
	public void setStockInicial(Float stockInicial) {
		this.stockInicial = stockInicial;
	}
	public Actividad getActividad() {
		return actividad;
	}
	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}
	public List<LoteAlmacen> getLoteAlmacenes() {
		return loteAlmacenes;
	}
	public void setLoteAlmacenes(List<LoteAlmacen> loteAlmacenes) {
		this.loteAlmacenes = loteAlmacenes;
	}
	
	public List<HistorialEstadoLote> getHistorialEstadoLote() {
		return historialEstadoLote;
	}
	
	public void setHistorialEstadoLote(List<HistorialEstadoLote> historialEstadoLote) {
		this.historialEstadoLote = historialEstadoLote;
	}
	
	public EstadoLote getEstadoActualLote() {
		return estadoActualLote;
	}
	public void setEstadoActualLote(EstadoLote estadoActualLote) {
		this.estadoActualLote = estadoActualLote;
	}
	public void addLoteAlmacenes(Almacen almacen, Float cantidad, String ubicacion){
		
		if (loteAlmacenes == null) {
			loteAlmacenes = new ArrayList<LoteAlmacen>();
		} 
		
		boolean iniciado = false;
		
			for (LoteAlmacen ld: loteAlmacenes){
				
				if(ld.getAlmacen().getCodAlmacen() == almacen.getCodAlmacen()){
				
					Float cant1= ld.getCantidad() + cantidad;
					
					ld.setCantidad(cant1);
					
					ld.setUbicacion(ubicacion);
					
					iniciado = true;
					
					cant1= this.getStockActual() + cantidad;
					
					this.setStockActual(cant1);
					
					
				}
				
			} 
			
			   	if(iniciado ==false){
			   		
			   		LoteAlmacen newOne = new LoteAlmacen();
					newOne.setLote(this);
					newOne.setAlmacen(almacen);
					newOne.setCantidad(cantidad);
					newOne.setUbicacion(ubicacion);
										
					loteAlmacenes.add(newOne);
					
			   	}
		
		
				
		
			
	}
	
	public void addHistorialEstadoLote(EstadoLote estadoL, String usuario) {

		if (historialEstadoLote == null) {
			historialEstadoLote = new ArrayList<HistorialEstadoLote>();
		}

		HistorialEstadoLote newOne = new HistorialEstadoLote();
		newOne.setLote(this);
		newOne.setEstadoLote(estadoL);
		newOne.setUsuario(usuario);
		Date date = new Date();
		//System.out.println(date);
		newOne.setFechaCambioEstado(date);

		
		historialEstadoLote.add(newOne);
		
		
		this.setEstadoActualLote(estadoL);

	}

	
   
}
