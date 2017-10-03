package com.articulo.modules;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.articulo.dto.ArticuloDTO;
import com.articulo.dto.ArticuloDTOFactory;
import com.articulo.entities.Articulo;
import com.articulo.entities.EstadoArticulo;
import com.articulo.repository.ArticuloRepository;
import com.articulo.repository.EstadoArticuloRepository;
import com.articulo.repository.GrupoRepository;
import com.articulo.repository.MaterialRepository;
import com.articulo.repository.TipoArticuloRepository;
import com.clases.propias.CapitalizeString;
import com.conversion.repository.UnidadMedidaRepository;
import com.institucional.module.PParcialService;

/**
 * Session Bean implementation class ArticuloServicesNew
 */
@Stateless
@LocalBean
public class ArticuloServicesNew implements ArticuloServicesNewRemote {

	@EJB
	private ArticuloServiceValidations validator;
	@EJB
	private ArticuloRepository articuloRepository;
	
	@EJB
	private UnidadMedidaRepository unidadRepository;
	
	@EJB
	private MaterialRepository materialRepository;
	
	@EJB
	private PParcialService ppaService;
	
	
	@EJB
	private TipoArticuloRepository tipoArticuloRepository;
	
	@EJB
	private GrupoRepository gRepository;
	
	@EJB
	private EstadoArticuloRepository estadoArticuloRepository;
	
	
    /**
     * Default constructor. 
     */
    public ArticuloServicesNew() {
        // TODO Auto-generated constructor stub
    }
    
	
    public void addArticulo(ArticuloDTO articulo){
		List<ValidationError> errors = validator.validarAddArticulo(articulo);
		if (errors.size()>0){
			throw new BusinessException("Errores al agregar el articulo",errors);
		}
		Articulo articuloNuevo = new Articulo();
		

		articuloNuevo.setCodClase(articulo.getCodClase());
		articuloNuevo.setCodItem(articulo.getCodItem());
		articuloNuevo.setDescArticulo(articulo.getDescArticulo());
		articuloNuevo.setNomArticulo(CapitalizeString.capitalizeString(articulo.getNomArticulo()));
		articuloNuevo.setNroSerie(articulo.getNroSerie());
		articuloNuevo.setVto(articulo.getVto());
		articuloNuevo.setPeso(articulo.getPeso());
		articuloNuevo.setNroParte(articulo.getNroParte());
		articuloNuevo.setMedidaU(unidadRepository.get(articulo.getMedidaU()));
		articuloNuevo.setIsComp(articulo.getIsComp());
		articuloNuevo.setPesoU(unidadRepository.get(articulo.getPesoUId()));
		articuloNuevo.setMaterial(materialRepository.get(articulo.getMaterialId()));
		articuloNuevo.setPartidaparcial(ppaService.getPPar(articulo.getPartidaParcialId()));
		articuloNuevo.setTipoArticulo(tipoArticuloRepository.get(articulo.getTipoArticuloId()));
		articuloNuevo.setGrupo(gRepository.get(articulo.getGrupoId()));
		articuloNuevo.setAct(articulo.getAct());
	
		articuloRepository.add(articuloNuevo);
		
		this.addHistorialEstadoArticulo(1, articuloNuevo.getCodArticulo(),articulo);
		
		}
    
    @Override
   	public ArticuloDTO findById(Integer articuloId) {
   		return ArticuloDTOFactory.getArticuloDTO(articuloRepository.get(articuloId));
   	}
   	@Override
   	public Collection<ArticuloDTO> listAll() {
   		return ArticuloDTOFactory.getArticuloDTO(articuloRepository.getAll());
   	}
   	
   	public void addHistorialEstadoArticulo(int idEstado, int idArticulo, ArticuloDTO articulo) {
	

		EstadoArticulo ea = estadoArticuloRepository.get(idEstado);
		Articulo art = articuloRepository.get(idArticulo);
		art.addHistorialEstado(ea, articulo.getUsuario());
	}


	public Articulo getDesArticulo(String descArticulo) {
		List<Articulo> articulos = articuloRepository.getDesArticulo(descArticulo);

		Articulo articulo = new Articulo();
		articulo = null;

		for (Articulo articulo1 : articulos) {

			if (articulos.size() == 1) {

				articulo = articulo1;
			}

		}

		return articulo;
	}
	

   	
    
    

}
