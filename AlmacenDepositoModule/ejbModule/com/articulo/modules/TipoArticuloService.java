package com.articulo.modules;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.articulo.dto.TipoArticuloDTO;
import com.articulo.dto.TipoArticuloFactory;
import com.articulo.entities.TipoArticulo;
import com.articulo.repository.TipoArticuloRepository;

/**
 * Session Bean implementation class TipoArticuloService
 */
@Stateless
@LocalBean
public class TipoArticuloService implements TipoArticuloServiceRemote {

    /**
     * Default constructor. 
     */
	
	@EJB
	private TipoArticuloRepository taRepository;

    public TipoArticuloService() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public TipoArticuloDTO findById(Integer tipoArticuloId) {
		return TipoArticuloFactory.getTipoArticuloDTO(taRepository.get(tipoArticuloId));
	}
	@Override
	public Collection<TipoArticuloDTO> listAll() {
		return TipoArticuloFactory.getTipoArticuloDTO(taRepository.getAll());
	}
	
	public void addTipoArticulo(TipoArticuloDTO tipoArticulo){
	/*	List<ValidationError> errors = validator.validarAddTipoArticulo(tipoArticulo);
		if (errors.size()>0){
			throw new BusinessException("Errores al agregar el tipoArticulo",errors);
		}*/
		TipoArticulo tipoArticuloNuevo = new TipoArticulo();
		

		tipoArticuloNuevo.setNomArticulo(tipoArticulo.getNomArticulo());
		tipoArticuloNuevo.setDescArticulo(tipoArticulo.getDescArticulo());
		
		taRepository.add(tipoArticuloNuevo);
		
	}
    


}
