package com.security;

import java.io.Serializable;
import java.util.Collection;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.seguridad.UsuariosServiceRemote;
import com.seguridad.dto.UsuarioDTO;

/**
 * Formulario que muestra la grilla de usuarios.
 * 
 * @author Gisella
 *
 */
@ManagedBean
@RequestScoped
public class UsuarioForm  implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private UsuariosServiceRemote usuarioService;

	public Collection<UsuarioDTO> getUsuarios() {
		return usuarioService.listAll();
	}
	
	public String desactivarUsuario(String login) {
		usuarioService.desactivarUsuario(login);
		return null;
	}
	
	public String activarUsuario(String login) {
		usuarioService.activarUsuario(login);
		return null;
	}
}
