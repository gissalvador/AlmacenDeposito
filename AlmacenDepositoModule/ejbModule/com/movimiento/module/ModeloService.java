package com.movimiento.module;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;


import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.movimiento.dto.ModeloDTO;
import com.movimiento.dto.ModeloFactory;
import com.movimiento.entities.Modelo;
import com.movimiento.repository.MarcaRepository;
import com.movimiento.repository.ModeloRepository;

/**
 * Session Bean implementation class ModeloService
 */
@Stateless
@LocalBean
public class ModeloService implements ModeloServiceRemote {

	@EJB
	private ModeloRepository moRepository;
	
	@EJB
	private MarcaRepository maRepository;
	
	@EJB
	private ModeloValidations validator;
	
    /**
     * Default constructor. 
     */
    public ModeloService() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
   	public ModeloDTO findById(Integer modeloId) {
   		return ModeloFactory.getModeloDTO(moRepository.get(modeloId));
   	}
    
   	@Override
   	public Collection<ModeloDTO> listAll() {
   		return ModeloFactory.getModeloDTO(moRepository.getAll());
   	}
   	
	@Override
   	public Collection<ModeloDTO> listAllModelos(Integer marcaId) {
   		return ModeloFactory.getModeloDTO(moRepository.getAllModelo(marcaId));
   	}
   	public void addModelo(ModeloDTO modelo){
   		List<ValidationError> errors = validator.validarAddModelo(modelo);
   		if (errors.size()>0){
   			throw new BusinessException("Errores al agregar el modelo",errors);
   		}
   		Modelo modeloNuevo = new Modelo();
   		

   		modeloNuevo.setNomModelo(modelo.getNomModelo());
   		modeloNuevo.setMarca(maRepository.get(modelo.getMarcaId()));
   	  		
   		moRepository.add(modeloNuevo);
   		
   	}

	public Modelo getnomModelo(String nomModelo, Integer marcaId) {
		// TODO Auto-generated method stub
		List<Modelo> modelos = moRepository.getNomModelo(nomModelo,
				marcaId);

		Modelo modelo = new Modelo();
		modelo = null;

		for (Modelo modelo1 : modelos) {

			if (modelos.size() == 1) {

				modelo = modelo1;
			}

		}

		return modelo;
	}

}
