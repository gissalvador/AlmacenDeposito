package com.institucional.module;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.clases.propias.CapitalizeString;
import com.institucional.dto.ProveedorDTO;
import com.institucional.dto.ProveedorFactory;
import com.institucional.entities.Proveedor;
import com.institucional.repository.ProveedorRepository;
import com.institucional.repository.TipoPersonaRepository;



/**
 * Session Bean implementation class ProveedorService
 */
@Stateless
@LocalBean
public class ProveedorService implements ProveedorServiceRemote {

    /**
     * Default constructor. 
     */
	
	@EJB
	private ProveedorRepository pRepository;
	
	@EJB
	private TipoPersonaRepository tRepository;
	
	@EJB
	private ProveedorValidations validator;

    public ProveedorService() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
  	public ProveedorDTO findById(Integer proveedorId) {
  		return ProveedorFactory.getProveedorDTO(pRepository.get(proveedorId));
  	}
  	@Override
  	public Collection<ProveedorDTO> listAll() {
  		return ProveedorFactory.getProveedorDTO(pRepository.getAll());
  	}
  	
  	public void addProveedor(ProveedorDTO proveedor){
  	List<ValidationError> errors = validator.validarAddProveedor(proveedor);
  		if (errors.size()>0){
  			throw new BusinessException("Errores al agregar el proveedor",errors);
  		}
  		Proveedor proveedorNuevo = new Proveedor();
  		

  		proveedorNuevo.setRazonSocial(CapitalizeString.capitalizeString(proveedor.getRazonSocial()));
  		proveedorNuevo.setCuit(proveedor.getCuit());
  		proveedorNuevo.setTelefono(proveedor.getTelefono());
  		proveedorNuevo.setCelular(proveedor.getCelular());
  		proveedorNuevo.setMail(proveedor.getMail());
  		proveedorNuevo.setDomicilio(proveedor.getDomicilio());
  		proveedorNuevo.setNroProveedor(proveedor.getNroProveedor());
  		proveedorNuevo.setAlias(CapitalizeString.capitalizeString(proveedor.getAlias()));
  		proveedorNuevo.setTipoPersona(tRepository.get(proveedor.getTipoPersona()));
  		  		
  		
  		pRepository.add(proveedorNuevo);
  		
  	}
  	
  	public Proveedor getProveedor(String nroProveedor){
		   List <Proveedor> proList = pRepository.getProveedor(nroProveedor);
		  
		   Proveedor pro= new Proveedor();
		   pro = null;
		   
		   for(Proveedor pro1: proList){
			   
			   if(proList.size() == 1){
				   
				   pro = pro1;
			   }
			   
		   }
		   
		   return pro;
	   }

	public Proveedor getCUIT(String cuit) {
		// TODO Auto-generated method stub
		List<Proveedor> proveedores = pRepository.getCUIT(cuit);

		Proveedor proveedor = new Proveedor();
		proveedor = null;

		for (Proveedor proveedor1 : proveedores) {

			if (proveedores.size() == 1) {

				proveedor = proveedor1;
			}

		}

		return proveedor;
	}

      
    

}
