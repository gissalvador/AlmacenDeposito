package com.articulo.modules;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;






import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.articulo.dto.MaterialDTO;
import com.articulo.dto.MaterialDTOFactory;
import com.articulo.entities.Material;
import com.articulo.repository.MaterialRepository;
import com.clases.propias.CapitalizeString;


/**
 * Session Bean implementation class MaterialService
 */
@Stateless
@LocalBean
public class MaterialService implements MaterialServiceRemote {
	
	@EJB
	private MaterialRepository materialRepository;
	
	@EJB
	private MaterialValidations validator;

    /**
     * Default constructor. 
     */
    public MaterialService() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public MaterialDTO findById(Integer materialId) {
		return MaterialDTOFactory.getMaterialDTO(materialRepository.get(materialId));
	}
	@Override
	public Collection<MaterialDTO> listAll() {
		return MaterialDTOFactory.getMaterialDTO(materialRepository.getAll());
	}
	
	public void addMaterial(MaterialDTO material){
	List<ValidationError> errors = validator.validarAddMaterial(material);
		if (errors.size()>0){
			throw new BusinessException("Errores al agregar el material",errors);
		}
		Material materialNuevo = new Material();
		

		
		materialNuevo.setNomMaterial(CapitalizeString.capitalizeString(material.getNomMaterial()));
		materialNuevo.setDesMaterial(CapitalizeString.capitalizeString(material.getDesMaterial()));
		materialNuevo.setTipoMaterial(CapitalizeString.capitalizeString(material.getTipoMaterial()));
		
		materialRepository.add(materialNuevo);
		
	}

	public Material getnomMaterial(String nomMaterial) {
		// TODO Auto-generated method stub
		List<Material> materiales = materialRepository.getNomMaterial(nomMaterial);

		Material material = new Material();
		material = null;

		for (Material material1 : materiales) {

			if (materiales.size() == 1) {

				material = material1;
			}

		}

		return material;
	}
	
	/**
	 * Obtiene un listado con todos los materials que corresponden a un material compuesto.
	 * 
	 * @param materialID
	 * @return
	 */


	/**
	 * Eliimina un material asociado a un material compuesto.
	 * 
	 * @param materialID
	 * @param artToDeleteID
	 
	public void eliminarMaterial(Integer materialID, int matToDeleteID){
		Material mat = materialRepository.get(materialID);
		mat.removeMaterial(materialRepository.get(matToDeleteID));
	
	}
	*/

    

}
