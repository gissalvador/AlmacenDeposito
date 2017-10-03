package com.movimiento.module;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.institucional.entities.Almacen;
import com.institucional.module.AlmacenValidations;
import com.movimiento.dto.MarcaDTO;
import com.movimiento.dto.MarcaFactory;
import com.movimiento.entities.Marca;
import com.movimiento.repository.MarcaRepository;

/**
 * Session Bean implementation class MarcaService
 */
@Stateless
@LocalBean
public class MarcaService implements MarcaServiceRemote {

	@EJB
	private MarcaRepository mRepository;
	
	@EJB
	private MarcaValidations validator;
    /**
     * Default constructor. 
     */
    public MarcaService() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
   	public MarcaDTO findById(Integer marcaId) {
   		return MarcaFactory.getMarcaDTO(mRepository.get(marcaId));
   	}
   	@Override
   	public Collection<MarcaDTO> listAll() {
   		return MarcaFactory.getMarcaDTO(mRepository.getAll());
   	}
   	
   	public void addMarca(MarcaDTO marca){
   		List<ValidationError> errors = validator.validarAddMarca(marca);
   		if (errors.size()>0){
   			throw new BusinessException("Errores al agregar el marca",errors);
   		}
   		Marca marcaNuevo = new Marca();
   		

   		marcaNuevo.setNomMarca(marca.getNomMarca());
   	  		
   		mRepository.add(marcaNuevo);
   		
   	}

	public Marca getnomMarca(String nomMarca) {
		// TODO Auto-generated method stub
		List<Marca> marcaes = mRepository.getNomMarca(nomMarca);

		Marca marca = new Marca();
		marca = null;

		for (Marca marca1 : marcaes) {

			if (marcaes.size() == 1) {

				marca = marca1;
			}

		}

		return marca;
	

	}
       

}
