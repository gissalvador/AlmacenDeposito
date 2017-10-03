package com.articulo.modules;

import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.application.exceptions.BusinessException;
import com.application.exceptions.ValidationError;
import com.articulo.dto.GrupoDTO;
import com.articulo.dto.GrupoDTOFactory;
import com.articulo.entities.Grupo;
import com.articulo.repository.GrupoRepository;

/**
 * Session Bean implementation class GrupoService
 */
@Stateless
@LocalBean
public class GrupoService implements GrupoServiceRemote {

	@EJB
	private GrupoRepository gRepository;

	@EJB
	private GrupoValidations validator;

	/**
	 * Default constructor.
	 */
	public GrupoService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public GrupoDTO findById(Integer grupoId) {
		return GrupoDTOFactory.getGrupoDTO(gRepository.get(grupoId));
	}

	@Override
	public Collection<GrupoDTO> listAll() {
		return GrupoDTOFactory.getGrupoDTO(gRepository.getAll());
	}

	public void addGrupo(GrupoDTO grupo) {

		List<ValidationError> errors = validator.validarAddGrupo(grupo);
		if (errors.size() > 0) {
			throw new BusinessException("Errores al agregar el grupo", errors);
		}

		Grupo grupoNuevo = new Grupo();

		grupoNuevo.setNomGrupo(grupo.getNomGrupo());
		grupoNuevo.setDescGrupo(grupo.getDesGrupo());

		gRepository.add(grupoNuevo);

	}

	public Grupo getnomGrupo(String nomGrupo) {
		// TODO Auto-generated method stub
		List<Grupo> grupos = gRepository.getNomGrupo(nomGrupo);

		Grupo grupo = new Grupo();
		grupo = null;

		for (Grupo grupo1 : grupos) {

			if (grupos.size() == 1) {

				grupo = grupo1;
			}

		}

		return grupo;
	}

}
