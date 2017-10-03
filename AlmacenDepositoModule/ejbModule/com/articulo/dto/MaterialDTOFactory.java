package com.articulo.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.articulo.entities.Material;


public class MaterialDTOFactory {
	
	public static MaterialDTO getMaterialDTO(Material material) {
		if(material == null) {
			return null;
		}

		MaterialDTO result = new MaterialDTO();
		result.setCodMaterial(material.getCodMaterial());
		result.setNomMaterial(material.getNomMaterial());
		result.setDesMaterial(material.getDesMaterial());
		result.setTipoMaterial(material.getTipoMaterial());
				
		return result;
	}
	
	public static Collection<MaterialDTO> getMaterialDTO(Collection<Material> materiales) {
		if(materiales == null) {
			return null;
		}

		
		List<MaterialDTO> retorno = new ArrayList<MaterialDTO>();
		for(Material material: materiales) {
			retorno.add(getMaterialDTO(material));
		}
		return retorno;
	}

}
