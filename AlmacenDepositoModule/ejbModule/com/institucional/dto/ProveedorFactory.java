package com.institucional.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.institucional.entities.Proveedor;



public class ProveedorFactory {
	
	public static ProveedorDTO getProveedorDTO(Proveedor proveedor) {
		if(proveedor == null) {
			return null;
		}

		ProveedorDTO result = new ProveedorDTO();
		result.setAlias(proveedor.getAlias());
		result.setCelular(proveedor.getCelular());
		result.setCodPersona(proveedor.getCodPersona());
		result.setCuit(proveedor.getCuit());
		result.setDomicilio(proveedor.getDomicilio());
		result.setTelefono(proveedor.getTelefono());
		result.setTipoPersona(proveedor.getTipoPersona().getCodTipoPersona());
		result.setMail(proveedor.getMail());
		result.setNroProveedor(proveedor.getNroProveedor());
		result.setRazonSocial(proveedor.getRazonSocial());
		
		
		
				
		return result;
	}
	
	public static Collection<ProveedorDTO> getProveedorDTO(Collection<Proveedor> proveedores) {
		if(proveedores == null) {
			return null;
		}

		
		List<ProveedorDTO> retorno = new ArrayList<ProveedorDTO>();
		for(Proveedor proveedor: proveedores) {
			retorno.add(getProveedorDTO(proveedor));
		}
		return retorno;
	}

}
